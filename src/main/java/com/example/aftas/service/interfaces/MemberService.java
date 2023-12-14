package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService {
    Member createMember(Member member);
    String generateMemberNumber(Member member);
    Member getMemberById(Long id);
    Member getMemberByNumber(Long number);
    Optional<Member> searchForMember(String searchWord);
    Page<Member> getAllMembers();
    Member updateMember(Long id, Member member);
    void deleteMember(Long id);
}
