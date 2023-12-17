package com.example.aftas.service.interfaces;

import com.example.aftas.controller.vm.Fish.FishRequestVM;
import com.example.aftas.entities.Fish;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FishService {
    Optional<Fish> getFishByName(String name);
    Fish createFish(FishRequestVM fishRequestVM);
    Page<Fish> getAllFishes();
    Optional<Fish> findFishById(Long id);
    Fish updateFish(Long id, FishRequestVM fishRequestVM);
    void deleteFish(Long id);
}
