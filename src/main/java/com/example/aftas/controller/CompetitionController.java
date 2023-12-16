package com.example.aftas.controller;

import com.example.aftas.controller.vm.*;
import com.example.aftas.controller.vm.Competition.CompetitionRequestVM;
import com.example.aftas.controller.vm.Competition.CompetitionResponseVM;
import com.example.aftas.controller.vm.Ranking.MemberToCompetitionRequestVM;
import com.example.aftas.controller.vm.Ranking.MemberToCompetitionResponseVM;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Ranking;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public ResponseEntity<?> createCompetition(@RequestBody @Valid CompetitionRequestVM competitionRequestVM){
        Competition competition = competitionService.createCompetition(competitionRequestVM.toCompetition());
        return GenericResponse.created(
                CompetitionResponseVM.fromCompetition(competition),
                "Competition created successfully");
    }

    @PostMapping("/register-in-competition")
    public ResponseEntity registerMemberInCompetition(@RequestBody MemberToCompetitionRequestVM memberToCompetitionRequestVM){
        Long memberId = memberToCompetitionRequestVM.memberId();
        String competitionId = memberToCompetitionRequestVM.competitionCode();
        MemberToCompetitionResponseVM memberToCompetitionResponseVM = MemberToCompetitionResponseVM.fromRanking(competitionService.registerMemberInACompetition(memberId, competitionId));
        return GenericResponse.created(memberToCompetitionResponseVM, "Member assigned successfully");
    }

    @GetMapping
    public ResponseEntity getAllCompetitions(){
        List<Competition> competitionList = competitionService.getAllCompetitions();
        if (competitionList.isEmpty()){
            return GenericResponse.notFound("No competitions were found");
        }
        List<CompetitionResponseVM> competitionResponseVMS = new ArrayList<>();
        competitionList.forEach(competition -> competitionResponseVMS.add(CompetitionResponseVM.fromCompetition(competition)));
        return GenericResponse.ok(competitionResponseVMS, "Competitions returned successfully") ;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> findCompetitionByCode(@PathVariable String code){
        Optional<Competition> competition = competitionService.findCompetitionByCode(code);
        if (competition.isEmpty()){
            throw new ResourceNotFoundException("Competition with the code {" + code + "} doesn't exist");
        }
        CompetitionResponseVM competitionResponseVM = CompetitionResponseVM.fromCompetition(competition.get());
        return GenericResponse.ok(competitionResponseVM, "Competition is found");
    }

    @PostMapping("/{competition}")
    public ResponseEntity generateCompetitionRanking(@PathVariable("competition") String competition){
        List<Ranking> generatedRankings = competitionService.generateCompetitionRanks(competition);
        List<RankingsResponseVM> rankingsResponseVMS = new ArrayList<>();
        generatedRankings.forEach(ranking -> rankingsResponseVMS.add(RankingsResponseVM.fromRanking(ranking)));
        return GenericResponse.ok(rankingsResponseVMS, "Generated Rankings");
    }

    @PostMapping("/{competition}/podium")
    public ResponseEntity showCompetitionPodium(@PathVariable("competition") String competition){
        List<Ranking> generatedRankings = competitionService.showCompetitionPodium(competition);
        List<RankingsResponseVM> rankingsResponseVMS = new ArrayList<>();
        generatedRankings.forEach(ranking -> rankingsResponseVMS.add(RankingsResponseVM.fromRanking(ranking)));
        return GenericResponse.ok(rankingsResponseVMS, "Generated Rankings");
    }
}
