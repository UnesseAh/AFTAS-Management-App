package com.example.aftas.controller.vm;

import com.example.aftas.entities.Competition;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionResponseVM(
        String CODE,
        LocalDate DATE,
        LocalTime START_TIME,
        LocalTime END_TIME,
        Integer NUMBER_OF_PARTICIPANTS,
        String LOCATION,
        Double AMOUNT
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
