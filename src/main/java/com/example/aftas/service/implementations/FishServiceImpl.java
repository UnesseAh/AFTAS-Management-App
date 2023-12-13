package com.example.aftas.service.implementations;

import com.example.aftas.entities.Fish;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.interfaces.FishService;
import org.springframework.stereotype.Service;

@Service
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;

    public FishServiceImpl(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    @Override
    public Fish getFishByName(String name) {
        return fishRepository.findFishByName(name);
    }
}
