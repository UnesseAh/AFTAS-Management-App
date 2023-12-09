package com.example.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity @Table(name = "Levels")
public class Level {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;
    private String description;
    private Integer points;
}
