package com.example.aftas.controller.vm;

import com.example.aftas.entities.Level;
import lombok.Getter;

public record LevelRequestVM(
        Integer code,
        String description,
        Integer points
) {
    public Level toLevel(){
        return new Level().builder()
                .code(code)
                .description(description)
                .points(points)
                .build();
    }
}
