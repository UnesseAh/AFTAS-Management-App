package com.example.aftas.repository;

import com.example.aftas.entities.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    @Query("SELECT h FROM Hunting h where h.competition.code = ?1 and h.member.number = ?2 and h.fish.id = ?3")
    Optional<Hunting> findHuntingByCompetitionAndMemberAndFish(String competition, Long member, Long fish);
}
