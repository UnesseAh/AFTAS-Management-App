package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Fish;
import org.springframework.stereotype.Service;

@Service
public interface FishService {
    Fish getFishByName(String name);
}
