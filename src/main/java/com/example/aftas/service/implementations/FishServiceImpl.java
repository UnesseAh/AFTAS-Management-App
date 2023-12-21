package com.example.aftas.service.implementations;

import com.example.aftas.controller.vm.Fish.FishRequestVM;
import com.example.aftas.entities.Fish;
import com.example.aftas.entities.Level;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.interfaces.FishService;
import com.example.aftas.service.interfaces.LevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Fish createFish(FishRequestVM fishRequestVM) {
        Optional<Fish> foundFish = getFishByName(fishRequestVM.name());
        if (foundFish.isPresent()){
            throw new IllegalArgumentException("There is already a fish with this name");
        }
        Optional<Level> level = levelService.findALevelByCode(fishRequestVM.LevelCode());
        if (level.isEmpty()){
            throw new IllegalArgumentException("There is no level with the code you provided");
        }
        Fish fish = Fish.builder()
                .name(fishRequestVM.name())
                .averageWeight(fishRequestVM.averageWeight())
                .level(level.get())
                .build();
        return fishRepository.save(fish);
    }

    @Override
    public Page<Fish> getAllFishes() {
        return fishRepository.findAll(PageRequest.of(0, 5));
    }

    @Override
    public Optional<Fish> findFishById(Long id) {
        return fishRepository.findById(id);
    }

    @Override
    public Fish updateFish(Long id, FishRequestVM fishRequestVM) {
        Optional<Fish> foundFish = findFishById(id);
        if (foundFish.isEmpty()){
            throw new IllegalArgumentException("Fish with this id doesn't exist");
        }

        Optional<Level> foundLevel = levelService.findALevelByCode(fishRequestVM.LevelCode());
        if (foundLevel.isEmpty()){
            throw new IllegalArgumentException("Level with this id doesn't exist");
        }

        foundFish.get().setName(fishRequestVM.name());
        foundFish.get().setAverageWeight(fishRequestVM.averageWeight());
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
