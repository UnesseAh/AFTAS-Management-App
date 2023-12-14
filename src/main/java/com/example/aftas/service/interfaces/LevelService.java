package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LevelService {
    Level createLevel(Level level);
    List<Level> getAllLevels();
    Optional<Level> findALevel(Long id);
    Level updateLevel(Long id, Level level);
    void deleteLevel(Long id);
}
