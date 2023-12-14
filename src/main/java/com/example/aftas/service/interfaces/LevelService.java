package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LevelService {
    Level createLevel(Level level);
    List<Level> getAllLevels();
    Level updateLevel(Long id, Level level);
    void deleteLevel(Long id);
}
