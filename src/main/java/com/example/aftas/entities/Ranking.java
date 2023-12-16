package com.example.aftas.entities;

import com.example.aftas.entities.embeddable.RankId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners({AuditingEntityListener.class})
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity @Table(name = "rankings")
public class Ranking {
    @EmbeddedId
    private RankId id;
    private Integer rank;
    private Integer score;
    @ManyToOne @JsonBackReference
    @MapsId("memberNumber")
    private Member member;
    @ManyToOne @JsonBackReference
    @MapsId("competitionCode")
    private Competition competition;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
