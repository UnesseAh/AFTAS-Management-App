package com.example.aftas.entities;

import com.example.aftas.entities.embeddable.RankId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity @Table(name = "rankings")

public class Ranking {
    @EmbeddedId
    private RankId id;
    private Integer rank;
    private Integer score;
    @ManyToOne
    @JsonBackReference
    @MapsId("memberNumber")
    private Member member;
    @ManyToOne
    @JsonBackReference
    @MapsId("competitionCode")
    private Competition competition;
}
