package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {

    void changeRankingScore(Competition competition, Member member, Integer fishScore);
    List<Ranking> getSortedRankingsByCompetition(Competition competition);

}
