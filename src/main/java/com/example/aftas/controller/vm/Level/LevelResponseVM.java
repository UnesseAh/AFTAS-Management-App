package com.example.aftas.controller.vm.Level;

import com.example.aftas.entities.Level;

public record LevelResponseVM(
        Integer code,
        String description,
        Integer points
) {
    public static LevelResponseVM fromLevel(Level level){
        return new LevelResponseVM(
                level.getCode(),
                level.getDescription(),
                level.getPoints()
        );
    }
}
