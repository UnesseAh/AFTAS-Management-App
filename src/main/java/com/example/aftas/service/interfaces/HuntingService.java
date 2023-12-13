package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Hunting;
import org.springframework.stereotype.Service;

@Service
public interface HuntingService {
    Hunting createHunting(Hunting hunting);
}
