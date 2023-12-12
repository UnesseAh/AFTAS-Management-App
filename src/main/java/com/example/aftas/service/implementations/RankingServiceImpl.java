package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.MemberService;
import com.example.aftas.service.interfaces.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;


    @Override
    public Ranking registerMemberInACompetition(Long memberId, String competitionCode) {
        Member member = memberService.getMemberById(memberId);
        Competition competition = competitionService.findCompetitionByCode(competitionCode);
        Ranking ranking = new Ranking().builder()
                .member(member)
                .competition(competition)
                .score(0)
                .rank(0)
                .build();
        return rankingRepository.save(ranking);
    }
}
