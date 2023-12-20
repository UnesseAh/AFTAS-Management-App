package com.example.aftas.controller.vm.Hunting;

import com.example.aftas.entities.Hunting;
import lombok.Builder;

@Builder
public record HuntingResponseVM(
        Long memberNumber,
        String memberName,
        String competition,
        String fishName,
        Integer huntScore,
        Integer numberOfFish
) {
    public static HuntingResponseVM fromHunting(Hunting hunting){
        return HuntingResponseVM.builder()
                .huntScore(hunting.getFish().getLevel().getPoints())
                .numberOfFish(hunting.getNumberOfFish())
                .fishName(hunting.getFish().getName())
                .memberName(hunting.getMember().getFirstName() + " " + hunting.getMember().getLastName())
                .memberNumber(hunting.getMember().getNumber())
                .competition(hunting.getCompetition().getCode())
                .build();
    }
}
