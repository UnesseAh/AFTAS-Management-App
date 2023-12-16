package com.example.aftas.repository;

import com.example.aftas.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Optional<Competition> findCompetitionByCodeContainsIgnoreCase(String code);
    Competition findCompetitionByCode(String code);
    @Query("SELECT c FROM Competition c WHERE c.date = ?1")
    Optional<Competition> findCompetitionByDate(LocalDate date);
}
