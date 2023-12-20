package com.example.aftas.controller;

import com.example.aftas.controller.vm.Register.RegisterResponseVM;
import com.example.aftas.controller.vm.Register.RegisterRequestVM;
import com.example.aftas.controller.vm.Ranking.RankingsResponseVM;
import com.example.aftas.entities.Ranking;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rankings")
public class RankingController {
    private final RankingService rankingService;


    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMemberInACompetition(@RequestBody RegisterRequestVM registerRequestVM){
        Ranking ranking = registerRequestVM.toRanking();
        return GenericResponse.created(
                RegisterResponseVM.fromRanking(rankingService.registerMember(ranking)),
                "Member registered successfully"
        );
    }

    @PostMapping("/{competition}")
    public ResponseEntity generateCompetitionRanking(@PathVariable("competition") String competition){
        List<Ranking> generatedRankings = rankingService.generateCompetitionRanks(competition);
        List<RankingsResponseVM> rankingsResponseVMS = new ArrayList<>();
        generatedRankings.forEach(ranking -> rankingsResponseVMS.add(RankingsResponseVM.fromRanking(ranking)));
        return GenericResponse.ok(rankingsResponseVMS, "Generated Rankings");
    }

    @PostMapping("/{competition}/podium")
    public ResponseEntity showCompetitionPodium(@PathVariable("competition") String competition){
        List<Ranking> generatedRankings = rankingService.showCompetitionPodium(competition);
        List<RankingsResponseVM> rankingsResponseVMS = new ArrayList<>();
        generatedRankings.forEach(ranking -> rankingsResponseVMS.add(RankingsResponseVM.fromRanking(ranking)));
        return GenericResponse.ok(rankingsResponseVMS, "Generated Rankings");
    }
}
