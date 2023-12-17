package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService {
    Member createMember(Member member);
    Optional<Member> getMemberById(Long id);
    Member getMemberByNumber(Long number);
    List<Member> searchForMember(String searchKey);
    Page<Member> getAllMembers();
    Member updateMember(Long id, Member member);
    void deleteMember(Long id);
}
