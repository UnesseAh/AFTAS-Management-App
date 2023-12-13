package com.example.aftas.controller.vm;

import com.example.aftas.entities.Hunting;
import lombok.Builder;

@Builder
public record HuntingResponseVM(
        Integer NUMBER_OF_FISH,
        String COMPETITION,
        String FISH_NAME,
        Long MEMBER_NUMBER
) {
    public static HuntingResponseVM fromHunting(Hunting hunting){
        return HuntingResponseVM.builder()
                .MEMBER_NUMBER(hunting.getMember().getNumber())
                .COMPETITION(hunting.getCompetition().getCode())
                .FISH_NAME(hunting.getFish().getName())
                .NUMBER_OF_FISH(hunting.getNumberOfFish())
                .build();
    }
}
