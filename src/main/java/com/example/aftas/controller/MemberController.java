package com.example.aftas.controller;

import com.example.aftas.controller.vm.Member.MemberRequestVM;
import com.example.aftas.controller.vm.Member.MemberResponseVM;
import com.example.aftas.entities.Member;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody @Valid MemberRequestVM memberRequestVM){
        Member member = memberService.createMember(memberRequestVM.toMember());
        return GenericResponse.created(MemberResponseVM.fromMember(member), "Member created successfully.");
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<?> searchForMember(@PathVariable("word") String searchWord){
        Optional<Member> member = memberService.searchForMember(searchWord);
        if (member.isEmpty()){
            throw new IllegalArgumentException("No member was found");
        }
        return GenericResponse.ok(MemberResponseVM.fromMember(member.get()), "Member was found");
    }

}
