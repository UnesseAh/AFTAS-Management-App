package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.interfaces.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public Competition createCompetition(Competition competition) {
        List<Competition> competitions = getAllCompetitions();
        List<Competition> foundDate = competitions.stream()
                .filter(c -> competition.getDate().equals(c.getDate())).toList();
        if(!foundDate.isEmpty()){
            throw new IllegalArgumentException("There is already a competition with  that date");
        }
        String generatedCode = generateCompetitionCode(competition);
        competition.setCode(generatedCode);
        competition.setNumberOfParticipants(0);
        return competitionRepository.save(competition);
    }

    @Override
    public String generateCompetitionCode(Competition competition) {
        String competitionCode = MessageFormat.format(
                "{0}-{1}",
                competition.getLocation().substring(0,4).toLowerCase(),
                competition.getDate());
        return competitionCode;
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return null;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition updateCompetition(Long id, Competition competition) {
        return null;
    }

    @Override
    public void deleteCompetition(Long id) {

    }
}
