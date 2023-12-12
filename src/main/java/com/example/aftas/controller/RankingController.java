package com.example.aftas.controller;

import com.example.aftas.controller.vm.MemberToCompetitionRequestVM;
import com.example.aftas.controller.vm.MemberToCompetitionResponseVM;
import com.example.aftas.entities.Ranking;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.interfaces.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ranks/")
public class RankingController {
    private final RankingService rankingService;

    @PostMapping("/assign-member")
    public ResponseEntity registerMemberInACompetition(@RequestBody MemberToCompetitionRequestVM memberToCompetitionRequestVM){
        Long memberId = memberToCompetitionRequestVM.memberId();
        String competitionId = memberToCompetitionRequestVM.competitionCode();
        MemberToCompetitionResponseVM memberToCompetitionResponseVM = MemberToCompetitionResponseVM.fromRanking(rankingService.registerMemberInACompetition(memberId, competitionId));
        return ResponseMessage.created(memberToCompetitionResponseVM, "Member assigned successfully");
    }
}
