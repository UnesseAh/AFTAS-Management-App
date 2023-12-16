package com.example.aftas.controller.vm.Competition;

import com.example.aftas.entities.Competition;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record CompetitionRequestVM(
        @NotNull(message = "Date is required")
        @Future(message = "Date must be in the future")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,
        @NotNull(message = "Start time is required")
        LocalTime startTime,
        @NotNull(message = "End time can't be null")
        LocalTime endTime,
        @NotBlank(message = "Location is required")
        @Pattern(regexp="[^0-9]*$",message = "Location must only contain characters")
        String location,
        @NotNull(message = "End time can't be null")
        @Min(value = 2, message = "Number of participants must be at least 2")
        Integer numberOfParticipants,
        @NotNull(message = "Amount is required")
        @Min(value = 0, message = "Amount can't be negative")
        @Digits(integer = 6, fraction = 2)
        Double amount
) {
    public Competition toCompetition(){
        return Competition.builder()
                .date(date)
                .startTime(startTime)
                .endTime(endTime)
                .location(location)
                .amount(amount)
                .build();
    }
}
