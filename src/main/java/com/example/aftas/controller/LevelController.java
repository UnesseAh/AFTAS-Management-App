package com.example.aftas.controller;

import com.example.aftas.controller.vm.Level.LevelRequestVM;
import com.example.aftas.controller.vm.Level.LevelResponseVM;
import com.example.aftas.entities.Level;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.LevelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity<?> createLevel(@RequestBody @Valid LevelRequestVM levelRequestVM){
        Level level = levelService.createLevel(levelRequestVM.toLevel());
        return GenericResponse.created(LevelResponseVM.fromLevel(level), "Level created successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllLevels(){
        Page<Level> levels = levelService.getAllLevels();
        if (levels.isEmpty()){
            return GenericResponse.notFound("No levels were found");
        }
        return GenericResponse.ok(levels.stream().map(LevelResponseVM::fromLevel).toList(), "Levels retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLevel(@PathVariable("id") Long id, @RequestBody LevelRequestVM levelRequestVM){
        Level level = levelRequestVM.toLevel();
        return GenericResponse.ok(
                LevelResponseVM.fromLevel(levelService.updateLevel(id, level)),
                "Level updated successfully") ;
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable("id") Long id){
        levelService.deleteLevel(id);
    }
}
