package com.example.aftas.service.implementations;

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
    public Ranking registerMemberInACompetition(Long memberId, String competitionId) {
        Optional<Member> member = memberService.getMemberById(memberId);
        return null;
    }
}
