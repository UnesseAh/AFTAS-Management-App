package com.example.aftas.service.implementations;

import com.example.aftas.entities.Level;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.interfaces.LevelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level createLevel(Level level) {
        Integer pointsOfSmallestLevel = levelRepository.getPointsOfSmallestLevel(level.getCode());
        Integer pointsOfLargestLevel = levelRepository.getPointsOfLargestLevel(level.getCode());

        if (pointsOfSmallestLevel == null) {
            return levelRepository.save(level);
        }

        if(pointsOfLargestLevel == null){
            if(level.getPoints() < pointsOfSmallestLevel){
                throw new IllegalArgumentException("Your level's points should be larger than (" + pointsOfSmallestLevel +")");
            }
        }

        if(level.getPoints() < pointsOfSmallestLevel || level.getPoints() > pointsOfLargestLevel){
            throw new IllegalArgumentException("Your level's points should be between (" + pointsOfSmallestLevel + ") and (" + pointsOfLargestLevel + ")");
        }

        return levelRepository.save(level);

    }

    @Override
    public Page<Level> getAllLevels() {
        return levelRepository.findAll(PageRequest.of(0,5));
    }

    @Override
    public Optional<Level> findALevel(Long id) {
        Optional<Level> level = levelRepository.findById(id);

        return level;
    }

    @Override
    public Optional<Level> findALevelByCode(Integer levelCode) {
        return levelRepository.getLevelByCode(levelCode);
    }

    @Override
    public Level updateLevel(Long id, Level level) {
        Optional<Level> foundLevel = findALevel(id);
        if (foundLevel.isEmpty()){
            throw new IllegalArgumentException("Level was not found");
        }

        level.setId(id);
        return levelRepository.save(level);
    }

    @Override
    public void deleteLevel(Long id) {
        Optional<Level> foundLevel = findALevel(id);
        if (foundLevel.isEmpty()){
            throw new IllegalArgumentException("Level was not found");
        }
        levelRepository.delete(foundLevel.get());
    }
}
