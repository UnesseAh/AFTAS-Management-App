package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.handler.exception.ValidationException;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.interfaces.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    public Hunting createHunting(Hunting hunting, Long weight) {

        Optional<Competition> foundCompetition = competitionService.findByCode(hunting.getCompetition().getCode());
        Optional<Member> foundMember = memberService.getMemberByNumber(hunting.getMember().getNumber());
        Optional<Fish> foundFish = fishService.getFishByName(hunting.getFish().getName());

        validateMemberAndCompetitionAndFish(foundCompetition, foundMember, foundFish, hunting, weight);

        Member member = foundMember.get() ; Competition competition = foundCompetition.get() ; Fish fish = foundFish.get() ;

        checkMemberIsAParticipant(member.getNumber(), competition.getCode());

        validateCompetitionStartTimeAndEndTime(competition);

        Hunting foundHunting = huntingRepository.findHuntingByCompetitionAndMemberAndFish(competition.getCode(), member.getNumber(), fish.getId()).orElse(null);

        Integer fishScore = fish.getLevel().getPoints();

        if (foundHunting != null ){
            foundHunting.setNumberOfFish(foundHunting.getNumberOfFish() + 1);
        }else {
            foundHunting = Hunting.builder()
                    .fish(fish)
                    .competition(competition)
                    .member(member)
                    .numberOfFish(1)
                    .build();
        }
        rankingService.changeRankingScore(competition, member, fishScore);
        return huntingRepository.save(foundHunting);
    }

    @Override
    public void validateMemberAndCompetitionAndFish(Optional<Competition> foundCompetition, Optional<Member> foundMember, Optional<Fish> foundFish, Hunting hunting, Long weight){
        List<String> errors = new ArrayList<>();
        if(foundCompetition.isEmpty()){
            errors.add("Competition with code {" + hunting.getCompetition().getCode() + "} doesn't exist");
        }
        if(foundMember.isEmpty()){
            errors.add("Member with number {" + hunting.getMember().getNumber() + "} doesn't exist");
        }
        if(foundFish.isEmpty()){
            errors.add("Fish with name {" + hunting.getFish().getName() + "} doesn't exist");
        }else if (foundFish.get().getAverageWeight() > weight){
            errors.add("Your fish's weight is less than the average weight : {" + foundFish.get().getAverageWeight() + "}");
        }
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }

    @Override
    public void validateCompetitionStartTimeAndEndTime(Competition competition) {
        LocalDateTime endDateTimeOfCompetition = LocalDateTime.of(competition.getDate(), competition.getEndTime());
        LocalDateTime startTimeOfCompetition = LocalDateTime.of(competition.getDate(), competition.getStartTime());

        if (LocalDateTime.now().isBefore(startTimeOfCompetition)){
            throw new IllegalArgumentException("This competition hasn't started yet");
        }
        if(endDateTimeOfCompetition.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("This competition is over");
        }
    }

    @Override
    public void validateFish(Optional<Fish> fish, Hunting hunting, Long weight) {

        if(fish.get().getAverageWeight() > weight){
            throw new IllegalArgumentException("Your fish's weight is not valid");
        }
    }

    @Override
    public void checkMemberIsAParticipant(Long number, String code) {
        if(rankingService.findRankingByMemberAndCompetition(number, code).isEmpty()) {
            throw new IllegalArgumentException("This member isn't a participant");
        }
    }

}
