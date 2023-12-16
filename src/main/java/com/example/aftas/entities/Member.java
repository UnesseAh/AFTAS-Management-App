package com.example.aftas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import com.example.aftas.entities.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EntityListeners({AuditingEntityListener.class})
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity @Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String firstName;
    private String lastName;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    @Column(unique = true)
    private String identityNumber;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @JsonManagedReference
    private List<Hunting> huntingList;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
//    @JsonManagedReference
//    private List<Ranking> rankings;
}
