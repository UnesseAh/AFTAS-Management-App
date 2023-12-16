package com.example.aftas.controller.vm.Ranking;

import jakarta.validation.constraints.NotBlank;

public record MemberToCompetitionRequestVM(
        @NotBlank(message = "Member number must not be empty")
        Long memberId,
        @NotBlank(message = "Competition code must not be empty")
        String competitionCode
) {
}
