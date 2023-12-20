package com.example.aftas.service.implementations;

import com.example.aftas.entities.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
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
    public Optional<Member> getMemberByNumber(Long number) {
        return memberRepository.findMemberByNumber(number);
    }

    @Override
    public List<Member> searchForMember(String searchKey) {
        return memberRepository.searchForAMember(searchKey);
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
         return memberRepository.findById(id);
    }

    @Override
    public Page<Member> getAllMembers() {
        return memberRepository.findAll(PageRequest.of(0,10));
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}
