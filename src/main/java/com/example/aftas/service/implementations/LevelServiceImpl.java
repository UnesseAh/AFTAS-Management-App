package com.example.aftas.service.implementations;

import com.example.aftas.entities.Level;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.interfaces.LevelService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
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
