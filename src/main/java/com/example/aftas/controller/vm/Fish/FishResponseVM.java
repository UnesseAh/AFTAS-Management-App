package com.example.aftas.controller.vm.Fish;

import com.example.aftas.entities.Fish;

public record FishResponseVM(
        String name,
        double averageWeight,
        Integer LevelCode
) {
    public static FishResponseVM fromFish(Fish fish){
        return new FishResponseVM(
                fish.getName(),
                fish.getAverageWeight(),
                fish.getLevel().getCode()
        );
    }
}
