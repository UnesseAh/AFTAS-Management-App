package com.example.aftas.controller.vm.Register;

import com.example.aftas.entities.Ranking;

public record RegisterResponseVM(
        String member,
        String competition,
        Integer score,
        Integer rank
) {
    public static RegisterResponseVM fromRanking(Ranking ranking){
        return new RegisterResponseVM(
                ranking.getMember().getFirstName() + " " + ranking.getMember().getLastName(),
                ranking.getCompetition().getCode(),
                ranking.getScore(),
                ranking.getRank()
        );
    }
}
