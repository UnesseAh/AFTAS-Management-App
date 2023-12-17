package com.example.aftas.service.implementations;

import com.example.aftas.entities.Competition;
import com.example.aftas.handler.exception.ValidationException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.interfaces.CompetitionService;
import com.example.aftas.service.interfaces.MemberService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final MemberService memberService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, MemberService memberService) {
        this.competitionRepository = competitionRepository;
        this.memberService = memberService;
    }

    @Override
    public Competition createCompetition(Competition competition) {

        validateCompetition(competition);

        String generatedCode = MessageFormat.format(
                "{0}-{1}",
                competition.getLocation().substring(0,4).toLowerCase(),
                competition.getDate());

        competition.setCode(generatedCode);
        competition.setNumberOfParticipants(competition.getNumberOfParticipants());
        return competitionRepository.save(competition);
    }

    @Override
    public Optional<Competition> findByCode(String code) {
        return competitionRepository.findCompetitionByCode(code);
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
    public void validateCompetition(Competition competition) {
        List<String> errors = new ArrayList<>();
        if(competitionRepository.findCompetitionByDate(competition.getDate()).isPresent()){
            errors.add("There is already a competition in the date (" + competition.getDate() + ")");
        }
        if(competition.getEndTime().isBefore(competition.getStartTime())){
            errors.add("the competition's end time must come after the start time");
        }
        if(Duration.between(competition.getStartTime(), competition.getEndTime()).toHours() < 1 ){
            errors.add("The competition must be at least 1 hour long");
        }
        if(competition.getDate().minus(Period.ofDays(2)).isBefore(LocalDate.now())){
            errors.add("The competition must at least be in 2 days from now");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }

    @Override
    public void deleteCompetition(Long id) {

    }
}
