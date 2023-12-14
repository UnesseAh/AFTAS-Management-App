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
    public Optional<Fish> getFishByName(String name) {
        return fishRepository.findFishByName(name);
    }

    @Override
    public Fish createFish(FishDTO fishDTO) {
        Optional<Fish> foundFish = getFishByName(fishDTO.name());
        if (foundFish.isPresent()){
            throw new IllegalArgumentException("There is already a fish with this name");
        }
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
        return fishRepository.findAll();
    }

    @Override
    public Optional<Fish> findFishById(Long id) {
        Optional<Fish> fish = fishRepository.findById(id);
        return fish;
    }

    @Override
    public Fish updateFish(Long id, FishDTO fishDTO) {
        Optional<Fish> foundFish = findFishById(id);
        if (foundFish.isEmpty()){
            throw new IllegalArgumentException("Fish with this id doesn't exist");
        }

        Optional<Level> foundLevel = levelService.findALevelByCode(fishDTO.LevelCode());
        if (foundLevel.isEmpty()){
            throw new IllegalArgumentException("Level with this id doesn't exist");
        }

        foundFish.get().setName(fishDTO.name());
        foundFish.get().setAverageWeight(fishDTO.averageWeight());
        foundFish.get().setLevel(foundLevel.get());

        return fishRepository.save(foundFish.get());
    }

    @Override
    public void deleteFish(Long id) {
        Optional<Fish> fish = findFishById(id);
        if (fish.isEmpty()){
            throw new IllegalArgumentException("Fish with this id doesn't exist");
        }
        fishRepository.delete(fish.get());
    }
}
