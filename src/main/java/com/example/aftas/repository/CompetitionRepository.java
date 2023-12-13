package com.example.aftas.repository;

import com.example.aftas.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findCompetitionByCodeContainsIgnoreCase(String code);
    Competition findCompetitionByCode(String code);
}
