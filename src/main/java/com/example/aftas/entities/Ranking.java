package com.example.aftas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity @Table(name = "rankings")

public class Ranking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rank;
    private Integer score;
    @ManyToOne
    @JsonBackReference
    private Member member;
    @ManyToOne
    @JsonBackReference
    private Competition competition;
}
