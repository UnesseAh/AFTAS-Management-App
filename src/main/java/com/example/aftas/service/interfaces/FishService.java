package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {
    Fish getFishByName(String name);
    Fish createFish(Fish fish);
    List<Fish> getAllFish();
    Fish updateFish(Fish fish);
    void deleteFish(Long id);
}
