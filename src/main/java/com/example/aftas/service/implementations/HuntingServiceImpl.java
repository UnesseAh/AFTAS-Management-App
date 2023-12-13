package com.example.aftas.service.implementations;

import com.example.aftas.entities.Hunting;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.interfaces.HuntingService;
import org.springframework.stereotype.Component;

@Component
public class HuntingServiceImpl implements HuntingService {
    private final HuntingRepository huntingRepository;

    public HuntingServiceImpl(HuntingRepository huntingRepository) {
        this.huntingRepository = huntingRepository;
    }

    @Override
    public Hunting createHunting(Hunting hunting) {
        return null;
    }
}
