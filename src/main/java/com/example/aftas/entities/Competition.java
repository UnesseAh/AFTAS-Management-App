package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity @Table(name = "competitions")
public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants;
    private String location;
    private Double amount;

    @CreatedDate
    private Long createdDate;
    @LastModifiedDate
    private Long modifiedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
    @JsonManagedReference
    private List<Hunting> huntingList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "competition")
    @JsonManagedReference
    private List<Ranking> rankings;
}
