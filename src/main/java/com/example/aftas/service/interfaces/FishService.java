package com.example.aftas.service.interfaces;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.entities.Fish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FishService {
    Optional<Fish> getFishByName(String name);
    Fish createFish(FishDTO fishDTO);
    List<Fish> getAllFishes();
    Optional<Fish> findFishById(Long id);
    Fish updateFish(Long id, FishDTO fishDTO);
    void deleteFish(Long id);
}
