package com.example.aftas.repository;

import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import com.example.aftas.service.interfaces.HuntingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    Optional<Hunting> findHuntingByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);
}
