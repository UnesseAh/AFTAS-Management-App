package com.example.aftas.controller.vm.Ranking;

import com.example.aftas.entities.Ranking;

public record RankingsResponseVM(
        Long memberNumber,
        String competitionCode,
        Integer score,
        Integer Rank
) {
    public static RankingsResponseVM fromRanking(Ranking ranking){
        return new RankingsResponseVM(
                ranking.getMember().getNumber(),
                ranking.getCompetition().getCode(),
                ranking.getScore(),
                ranking.getRank()
        );
    }
}
