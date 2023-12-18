package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CompetitionService {
    Competition createCompetition(Competition competition);
    Optional<Competition> findByCode(String code);
    Page<Competition> getAllCompetitions();
    Competition updateCompetition(Long id, Competition competition);
    void validateCompetition(Competition competition);
    void deleteCompetition(Long id);
}
