package com.example.aftas.controller;

import com.example.aftas.controller.vm.LevelRequestVM;
import com.example.aftas.controller.vm.LevelResponseVM;
import com.example.aftas.entities.Level;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.interfaces.LevelService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/levels")
public class LevelController {

    private final LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity createLevel(@RequestBody @Valid LevelRequestVM levelRequestVM){
        Level level = levelService.createLevel(levelRequestVM.toLevel());
        return ResponseMessage.created(LevelResponseVM.fromLevel(level), "Level created successfully");
    }

    @GetMapping
    public ResponseEntity getAllLevels(){
        List<Level> levels = levelService.getAllLevels();
        if (levels.isEmpty()){
            return ResponseMessage.notFound("No levels were found");
        }
        return ResponseMessage.ok(levels.stream().map(LevelResponseVM::fromLevel).toList(), "Levels retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLevel(@PathVariable("id") Long id, @RequestBody LevelRequestVM levelRequestVM){
        Level level = levelRequestVM.toLevel();
        return ResponseMessage.ok(
                LevelResponseVM.fromLevel(levelService.updateLevel(id, level)),
                "Level updated successfully") ;
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable("id") Long id){
        levelService.deleteLevel(id);
    }
}
