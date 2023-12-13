package com.example.aftas.service.interfaces;

import com.example.aftas.DTO.HuntingDTO;
import com.example.aftas.entities.Hunting;
import org.springframework.stereotype.Service;

@Service
public interface HuntingService {
    Hunting createHunting(HuntingDTO huntingDTO);
}
