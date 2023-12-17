package com.example.aftas.controller.vm.Ranking;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Member;
import com.example.aftas.entities.Ranking;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestVM(
        @NotBlank(message = "Member number is required")
        Long memberNumber,
        @NotBlank(message = "Competition code is required")
        String competitionCode
) {
        public Ranking toRanking(){
                return Ranking.builder()
                        .member(Member.builder().number(memberNumber).build())
                        .competition(Competition.builder().code(competitionCode).build())
                        .build();
        }
}
