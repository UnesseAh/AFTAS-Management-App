package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Ranking;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface RankingService {
    Ranking registerMemberInACompetition(Long memberId, String competitionId);
}
