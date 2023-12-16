package com.example.aftas.controller;

import com.example.aftas.controller.vm.Member.MemberRequestVM;
import com.example.aftas.controller.vm.Member.MemberResponseVM;
import com.example.aftas.entities.Member;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/search/{search_key}")
    public ResponseEntity<?> searchForMember(@PathVariable("search_key") String searchWord){
        List<Member> membersList = memberService.searchForMember(searchWord);
        if (membersList.isEmpty()){
            throw new ResourceNotFoundException("No members were found");
        }
        final List<MemberResponseVM> memberResponseVMS = new ArrayList<>();
        membersList.forEach(member -> memberResponseVMS.add(MemberResponseVM.fromMember(member)));

        return GenericResponse.ok(memberResponseVMS,"Member was found");
    }

}
