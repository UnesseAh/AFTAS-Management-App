package com.example.aftas.repository;

import com.example.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.number = ?1")
    Member findMemberByNumber(Long number);

    @Query(value = "SELECT * FROM members " +
            "WHERE members.first_name LIKE ?1 " +
            "or members.last_name like ?1 " +
            "or CONVERT(members.number, CHAR) like ?1",
            nativeQuery = true)
    List<Member> searchForAMember(String searchWord);

    @Query("SELECT m FROM Member m WHERE m.identityNumber = ?1")
    Optional<Member> findMemberByIdentityNumber(String identityNumber);

}
