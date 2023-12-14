package com.example.aftas.service.implementations;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.interfaces.FishService;
import com.example.aftas.service.interfaces.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FishServiceImpl implements FishService {
    private final FishRepository fishRepository;
    private final LevelService levelService;

    public FishServiceImpl(FishRepository fishRepository, LevelService levelService) {
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }

    @Override
    public Fish getFishByName(String name) {
        return fishRepository.findFishByName(name);
    }

    @Override
    public Fish createFish(FishDTO fishDTO) {
        Optional<Level> level = levelService.findALevelByCode(fishDTO.LevelCode());
        if (level.isEmpty()){
            throw new IllegalArgumentException("There is no level with the code you provided");
        }
        Fish fish = Fish.builder()
                .name(fishDTO.name())
                .averageWeight(fishDTO.averageWeight())
                .level(level.get())
                .build();
        return fishRepository.save(fish);
    }

    @Override
    public List<Fish> getAllFishes() {
        return null;
    }

    @Override
    public Fish updateFish(Long id, Fish fish) {
        return null;
    }

    @Override
    public void deleteFish(Long id) {

    }
}
