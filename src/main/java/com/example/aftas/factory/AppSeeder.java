package com.example.aftas.factory;

import com.example.aftas.factory.dbSeeder.FishSeeder;
import com.example.aftas.factory.dbSeeder.LevelSeeder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppSeeder {
    private final FishSeeder fishSeeder;
    private final LevelSeeder levelSeeder;
    public AppSeeder(FishSeeder fishSeeder, LevelSeeder levelSeeder) {
        this.fishSeeder = fishSeeder;
        this.levelSeeder = levelSeeder;
    }
    @PostConstruct
    public void init() {
        levelSeeder.seed();
        fishSeeder.seed();
    }
}
