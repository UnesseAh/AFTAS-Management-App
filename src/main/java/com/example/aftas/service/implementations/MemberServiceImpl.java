package com.example.aftas.service.implementations;

import com.example.aftas.entities.Member;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public String generateMemberNumber(Member member) {
        String generatedString = member.getFirstName().substring(0,2) + "-" +
                        member.getLastName().substring(0,2) + "-" +
                        RandomStringUtils.randomAlphanumeric(4);
        return generatedString;
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
    public List<Member> getAllMembers() {
        return null;
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}
