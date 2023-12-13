package com.example.aftas.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class RankId implements Serializable {

    Long memberNumber;
    String competitionCode;
}
