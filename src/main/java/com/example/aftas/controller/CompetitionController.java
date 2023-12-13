package com.example.aftas.controller;

import com.example.aftas.controller.vm.*;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Ranking;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.interfaces.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity createCompetition(@RequestBody @Valid CompetitionRequestVM competitionRequestVM){

        Competition competition = competitionService.createCompetition(competitionRequestVM.toCompetition());
        return ResponseMessage.created(
                CompetitionResponseVM.fromCompetition(competition),
                "Competition created successfully");
    }

    @PostMapping("/assign-member-in-competition")
    public ResponseEntity registerMemberInACompetition(@RequestBody MemberToCompetitionRequestVM memberToCompetitionRequestVM){
        Long memberId = memberToCompetitionRequestVM.memberId();
        String competitionId = memberToCompetitionRequestVM.competitionCode();
        MemberToCompetitionResponseVM memberToCompetitionResponseVM = MemberToCompetitionResponseVM.fromRanking(competitionService.registerMemberInACompetition(memberId, competitionId));
        return ResponseMessage.created(memberToCompetitionResponseVM, "Member assigned successfully");
    }

    @GetMapping
    public ResponseEntity getAllCompetitions(){
        List<Competition> competitionList = competitionService.getAllCompetitions();
        if (competitionList.isEmpty()){
            return ResponseMessage.notFound("No competitions were found");
        }
        List<CompetitionResponseVM> competitionResponseVMS = new ArrayList<>();
        competitionList.forEach(competition -> competitionResponseVMS.add(CompetitionResponseVM.fromCompetition(competition)));
        return ResponseMessage.ok(competitionResponseVMS, "Competitions returned successfully") ;
    }

    @GetMapping("/{code}")
    public ResponseEntity getCompetition(@PathVariable String code){
        CompetitionResponseVM competitionResponseVM = CompetitionResponseVM.fromCompetition(competitionService.findCompetitionByCode(code));
        return ResponseMessage.ok(competitionResponseVM, "Competition is found");
    }
    @PostMapping("/{competition}")
    public ResponseEntity generateCompetitionRanking(@PathVariable("competition") String competition){
        List<Ranking> generatedRankings = competitionService.generateCompetitionRanks(competition);
        List<RankingsResponseVM> rankingsResponseVMS = new ArrayList<>();
        generatedRankings.forEach(ranking -> rankingsResponseVMS.add(RankingsResponseVM.fromRanking(ranking)));
        return ResponseMessage.ok(rankingsResponseVMS, "Generated Rankings");
    }
}
