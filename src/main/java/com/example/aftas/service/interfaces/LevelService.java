package com.example.aftas.service.interfaces;

import com.example.aftas.entities.Level;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LevelService {
    Level createLevel(Level level);
    Page<Level> getAllLevels();
    Optional<Level> findALevel(Long id);
    Optional<Level> findALevelByCode(Integer levelCode);
    Level updateLevel(Long id, Level level);
    void deleteLevel(Long id);
}
