package com.example.aftas.service.implementations;

import com.example.aftas.DTO.HuntingDTO;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.interfaces.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final CompetitionService competitionService;
    private final MemberService memberService;
    private final FishService fishService;
    private final RankingService rankingService;

    public HuntingServiceImpl(HuntingRepository huntingRepository, CompetitionService competitionService, MemberService memberService, FishService fishService, RankingService rankingService) {
        this.huntingRepository = huntingRepository;
        this.competitionService = competitionService;
        this.memberService = memberService;
        this.fishService = fishService;
        this.rankingService = rankingService;
    }


    @Override
    public Hunting createHunting(HuntingDTO huntingDTO) {

        Competition competition = competitionService.findCompetitionByCode(huntingDTO.competitionCode()).get();

        LocalDateTime endDateTimeOfCompetition = LocalDateTime.of(competition.getDate(), competition.getEndTime());
        if(LocalDateTime.now().isAfter(endDateTimeOfCompetition)){
            throw new IllegalArgumentException("This competition is over");
        }

        Member member = memberService.getMemberByNumber(huntingDTO.memberNumber());
        Optional<Fish> fish = fishService.getFishByName(huntingDTO.fishName());

        if(fish.get().getAverageWeight() > huntingDTO.weight()){
            throw new IllegalArgumentException("Your fish's weight is not valid");
        }

        Optional<Hunting> foundHunting = huntingRepository.findHuntingByCompetitionAndMemberAndFish(competition, member, fish);
        Integer fishScore = fish.get().getLevel().getPoints();

        if (foundHunting.isPresent()){
            Hunting updatedHunting = foundHunting.get();
            updatedHunting.setNumberOfFish(foundHunting.get().getNumberOfFish() + 1);
            rankingService.changeRankingScore(competition, member,fishScore);
            return huntingRepository.save(updatedHunting);
        }else {
            Hunting hunting = Hunting.builder()
                    .fish(fish.get())
                    .competition(competition)
                    .member(member)
                    .numberOfFish(1)
                    .build();

            rankingService.changeRankingScore(competition, member,fishScore);

            return huntingRepository.save(hunting);
        }

    }
}
