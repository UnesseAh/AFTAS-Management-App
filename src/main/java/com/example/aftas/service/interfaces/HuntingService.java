package com.example.aftas.service.interfaces;

import com.example.aftas.DTO.HuntingDTO;
import com.example.aftas.entities.Competition;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Hunting;
import com.example.aftas.entities.Member;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HuntingService {
    Hunting createHunting(HuntingDTO huntingDTO);
    Optional<Hunting> checkHunting(Competition competition, Member member, Fish fish);
}
