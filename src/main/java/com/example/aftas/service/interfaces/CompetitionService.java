package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Competition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompetitionService {
    Competition createCompetition(Competition competition);
    String generateCompetitionCode(Competition competition);
    Competition findCompetitionByCode(String code);
    List<Competition> getAllCompetitions();
    Competition updateCompetition(Long id, Competition competition);
    void deleteCompetition(Long id);
}
