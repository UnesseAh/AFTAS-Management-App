package com.example.aftas.controller;

import com.example.aftas.controller.vm.Member.MemberRequestVM;
import com.example.aftas.controller.vm.Member.MemberResponseVM;
import com.example.aftas.entities.Member;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.MemberService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> getAllMembers(){
        Page<Member> memberList = memberService.getAllMembers();
        if (memberList.isEmpty()) {
            return GenericResponse.notFound("No members were found");
        }
        List<MemberResponseVM> result = new ArrayList<>();
        memberList.forEach(member -> result.add(MemberResponseVM.fromMember(member)));
        return GenericResponse.ok(result, "Members were retrieved successfully");
    }
}
