package com.example.aftas.service.implementations;

import com.example.aftas.entities.Level;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.interfaces.LevelService;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Level updateLevel(Long id, Level level) {
        return null;
    }

    @Override
    public void deleteLevel(Long id) {

    }
}
