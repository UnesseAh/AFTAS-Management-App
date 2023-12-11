package com.example.aftas.controller;

import com.example.aftas.controller.vm.CompetitionRequestVM;
import com.example.aftas.controller.vm.CompetitionResponseVM;
import com.example.aftas.entities.Competition;
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

    @GetMapping
    public ResponseEntity getAllCompetitions(){
        List<Competition> competitionList = competitionService.getAllCompetitions();
        List<CompetitionResponseVM> competitionResponseVMS = new ArrayList<>();
        competitionList.forEach(competition -> competitionResponseVMS.add(CompetitionResponseVM.fromCompetition(competition)));
        return ResponseMessage.ok(competitionResponseVMS, "All competitions") ;
    }
}
