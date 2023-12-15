package com.example.aftas.controller.vm.Competition;

import com.example.aftas.entities.Competition;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionRequestVM(
        @NotNull(message = "Date can't be null")
        @Future(message = "Date must be in the future")
        LocalDate date,
        @NotNull(message = "Start time can't be null")
        LocalTime startTime,
        @NotNull(message = "End time can't be null")
        LocalTime endTime,
        @NotNull @NotBlank
        String location,
        @NotNull(message = "Amount can't be null")
        Double amount
) {
    public Competition toCompetition(){
        return new Competition().builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .location(location)
                .amount(amount)
                .build();
    }
}
