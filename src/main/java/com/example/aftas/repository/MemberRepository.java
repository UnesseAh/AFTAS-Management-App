package com.example.aftas.repository;

import com.example.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberByNumber(Long number);
    @Query(value = "SELECT * FROM members " +
            "WHERE members.first_name LIKE ?1 " +
            "or members.last_name like ?1 " +
            "or CONVERT(members.number, CHAR) like ?1",
    nativeQuery = true)
    Optional<Member> searchForAMember(String searchWord);
}
