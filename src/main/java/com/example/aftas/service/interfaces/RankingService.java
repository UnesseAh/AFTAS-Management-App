package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

@Service
public interface RankingService {

    void changeRankingScore(Competition competition, Member member, Integer fishScore);

}
