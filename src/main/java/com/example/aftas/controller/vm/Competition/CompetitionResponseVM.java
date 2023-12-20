package com.example.aftas.controller.vm.Competition;

import com.example.aftas.entities.Competition;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionResponseVM(
        String code,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        Integer numberOfParticipants,
        String location,
        Double amount
) {
    public static CompetitionResponseVM fromCompetition(Competition competition){
        return new CompetitionResponseVM(
                competition.getCode(),
                competition.getDate(),
                competition.getStartTime(),
                competition.getEndTime(),
                competition.getNumberOfParticipants(),
                competition.getLocation(),
                competition.getAmount()
        );
    }
}
