package com.example.aftas.controller;

import com.example.aftas.controller.vm.MemberResponseVM;
import com.example.aftas.entities.Member;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(@RequestBody Member member){
        return ResponseMessage.created(
                MemberResponseVM.fromMember(memberService.createMember(member)
                ), "Member created successfully!");
    }
}
