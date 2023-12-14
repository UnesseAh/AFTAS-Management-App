package com.example.aftas.service.interfaces;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {
    Fish getFishByName(String name);
    Fish createFish(FishDTO fishDTO);
    List<Fish> getAllFishes();
    Fish updateFish(Long id, Fish fish);
    void deleteFish(Long id);
}
