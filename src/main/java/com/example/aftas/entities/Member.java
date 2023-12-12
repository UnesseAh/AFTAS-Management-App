package com.example.aftas.entities;

import com.example.aftas.entities.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@EntityListeners({AuditingEntityListener.class})
@Entity @Table(name = "members")
//@JsonIgnoreProperties(value = { "rankings" })

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;
    private String firstName;
    private String lastName;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @JsonManagedReference
    private List<Hunting> huntingList;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @JsonManagedReference
    private List<Ranking> rankings;
}
