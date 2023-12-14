package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import com.example.aftas.repository.RankingRepository;

import com.example.aftas.service.interfaces.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;

    public RankingServiceImpl(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public void changeRankingScore(Competition competition, Member member, Integer fishScore) {
        Ranking ranking = rankingRepository.findRankingByCompetitionAndMember(competition,member);
        ranking.setScore(ranking.getScore() + fishScore);
        rankingRepository.save(ranking);
    }

    @Override
    public List<Ranking> getSortedRankingsByCompetition(Competition competition) {
        return rankingRepository.findRankingsByCompetitionOrderByScoreDesc(competition);
    }
}
