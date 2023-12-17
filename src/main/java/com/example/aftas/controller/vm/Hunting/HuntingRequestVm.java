package com.example.aftas.controller.vm.Hunting;


import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record HuntingRequestVm(
        @NotBlank(message = "Competition code is required")
        String competitionCode,
        @NotNull(message = "Numbers is required")
        Long memberNumber,
        @NotBlank(message = "Fish name is required")
        @Pattern(regexp="[^0-9]*$",message = "Last name must only contain characters")
        String fishName,
        @NotNull(message = "Weight is required")
        Long weight
) {
        public Hunting toHunting(){
                return Hunting.builder()
                        .member(Member.builder().number(memberNumber).build())
                        .competition(Competition.builder().code(competitionCode).build())
                        .fish(Fish.builder().name(fishName).build())
                        .build();
        }
}
