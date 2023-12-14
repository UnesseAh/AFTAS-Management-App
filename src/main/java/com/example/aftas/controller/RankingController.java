package com.example.aftas.controller;

import com.example.aftas.service.interfaces.RankingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ranks/")
public class RankingController {
    private final RankingService rankingService;


    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }
}
