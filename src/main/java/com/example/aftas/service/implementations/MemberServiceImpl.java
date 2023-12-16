package com.example.aftas.service.implementations;

import com.example.aftas.entities.Member;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) {
        Optional<Member> memberFound = memberRepository.findMemberByIdentityNumber(member.getIdentityNumber());

        if(memberFound.isPresent()){
            throw new IllegalArgumentException("Member with the identity number {" + member.getIdentityNumber() + "} already exists");
        }

        member.setAccessionDate(LocalDate.now());

        return memberRepository.save(member);
    }

    @Override
    public Member getMemberByNumber(Long number) {
        return memberRepository.findMemberByNumber(number);
    }

    @Override
    public Optional<Member> searchForMember(String searchWord) {
        return memberRepository.searchForAMember(searchWord);
    }

    @Override
    public Member getMemberById(Long id) {
         Optional<Member> member = memberRepository.findById(id);
         if (member.isEmpty()){
             throw new ResourceNotFoundException("Member not found");
         }
         return member.get();
    }


    @Override
    public Page<Member> getAllMembers() {
        return memberRepository.findAll(PageRequest.of(0,3));
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}
