package com.example.aftas.controller.vm;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;


public record MemberToCompetitionResponseVM(
        String member,
        String competition,
        Integer score,
        Integer rank
) {
    public static MemberToCompetitionResponseVM fromRanking(Ranking ranking){
        return new MemberToCompetitionResponseVM(
                ranking.getMember().getFirstName() + " " + ranking.getMember().getLastName(),
                ranking.getCompetition().getCode(),
                ranking.getScore(),
                ranking.getRank()
        );
    }
}
