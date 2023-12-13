package com.example.aftas.service.implementations;

import com.example.aftas.DTO.HuntingDTO;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.FishService;
import com.example.aftas.service.interfaces.HuntingService;
import com.example.aftas.service.interfaces.MemberService;
import org.springframework.stereotype.Component;

@Component
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;
    private final CompetitionService competitionService;
    private final MemberService memberService;
    private final FishService fishService;

    public HuntingServiceImpl(HuntingRepository huntingRepository, CompetitionService competitionService, MemberService memberService, FishService fishService) {
        this.huntingRepository = huntingRepository;
        this.competitionService = competitionService;
        this.memberService = memberService;
        this.fishService = fishService;
    }


    @Override
    public Hunting createHunting(HuntingDTO huntingDTO) {
        Competition competition = competitionService.findCompetitionByCode(huntingDTO.competitionCode());
        Member member = memberService.getMemberByNumber(huntingDTO.memberNumber());
        Fish fish = fishService.getFishByName(huntingDTO.fishName());
        if(fish.getAverageWeight() > huntingDTO.weight()){
            throw new IllegalArgumentException("You fish's weight is not valid");
        }

        Hunting hunting = Hunting.builder()
                .fish(fish)
                .competition(competition)
                .member(member)
                .numberOfFish(0)
                .build();

        return huntingRepository.save(hunting);
    }
}
