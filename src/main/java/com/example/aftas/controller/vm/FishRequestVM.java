package com.example.aftas.controller.vm;

import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;

public record FishRequestVM(
        String name,
        double averageWeight,
        Level LevelCode
) {
    public Fish toFish(){
        return null;
    }
}
