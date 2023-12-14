package com.example.aftas.repository;

import com.example.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByNumber(Long number);

    Optional<Member> searchForAMember(String searchWord);
}
