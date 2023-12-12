package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

@Service
public interface RankingService {
    Ranking registerMemberInACompetition(Long memberId, String competitionId);
    void checkIfMemberAlreadyEnrolledInACompetition(Member member, Competition competition);
    void checkCompetitionDateIsNotOver(Competition competition);
}
