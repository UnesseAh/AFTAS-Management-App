package com.example.aftas.controller;

import com.example.aftas.controller.vm.Competition.CompetitionRequestVM;
import com.example.aftas.controller.vm.Competition.CompetitionResponseVM;
import com.example.aftas.entities.Competition;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public ResponseEntity<?> getAllCompetitions(){
        Page<Competition> competitionList = competitionService.getAllCompetitions();
        if (competitionList.isEmpty()){
            return GenericResponse.notFound("No competitions were found");
        }
        List<CompetitionResponseVM> competitionResponseVMS = new ArrayList<>();
        competitionList.forEach(competition -> competitionResponseVMS.add(CompetitionResponseVM.fromCompetition(competition)));
        return GenericResponse.ok(competitionResponseVMS, "Competitions returned successfully") ;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> findCompetitionByCode(@PathVariable String code){
        Optional<Competition> competition = competitionService.findByCode(code);
        if (competition.isEmpty()){
            throw new ResourceNotFoundException("Competition with the code {" + code + "} doesn't exist");
        }
        CompetitionResponseVM competitionResponseVM = CompetitionResponseVM.fromCompetition(competition.get());
        return GenericResponse.ok(competitionResponseVM, "Competition is found");
    }
}
