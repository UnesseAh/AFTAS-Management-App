package com.example.aftas.controller.vm.Hunting;

import com.example.aftas.entities.Hunting;
import lombok.Builder;

@Builder
public record HuntingResponseVM(
        Integer HUNT_SCORE,
        Integer NUMBER_OF_FISH,
        String FISH_NAME,
        String MEMBER_NAME,
        Long MEMBER_NUMBER,
        String COMPETITION
) {
    public static HuntingResponseVM fromHunting(Hunting hunting){
        return HuntingResponseVM.builder()
                .HUNT_SCORE(hunting.getFish().getLevel().getPoints())
                .NUMBER_OF_FISH(hunting.getNumberOfFish())
                .FISH_NAME(hunting.getFish().getName())
                .MEMBER_NAME(hunting.getMember().getFirstName() + " " + hunting.getMember().getLastName())
                .MEMBER_NUMBER(hunting.getMember().getNumber())
                .COMPETITION(hunting.getCompetition().getCode())
                .build();
    }
}
