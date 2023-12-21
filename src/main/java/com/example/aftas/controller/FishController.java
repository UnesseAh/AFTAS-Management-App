package com.example.aftas.controller;

import com.example.aftas.controller.vm.Fish.FishRequestVM;
import com.example.aftas.controller.vm.Fish.FishResponseVM;
import com.example.aftas.entities.Fish;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.FishService;
import com.example.aftas.service.interfaces.LevelService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/fishes")
public class FishController {

    private final FishService fishService;
    private final LevelService levelService;

    public FishController(FishService fishService, LevelService levelService) {
        this.fishService = fishService;
        this.levelService = levelService;
    }

    @PostMapping
    public ResponseEntity createFish(@RequestBody @Valid FishRequestVM fishRequestVM){
        Fish fish = fishService.createFish(fishRequestVM);
        return GenericResponse.created(FishResponseVM.fromFish(fish), "Fish created successfully");
    }

    @GetMapping
    public ResponseEntity getAllFish(){
        Page<Fish> fishes = fishService.getAllFishes();
        if (fishes.isEmpty()){
            return GenericResponse.notFound("No fishes were found");
        }
        return GenericResponse.ok(fishes.stream().map(FishResponseVM::fromFish).toList(), "Fishes retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFish(@PathVariable("id") Long id, @RequestBody FishRequestVM fishRequestVM){

        return GenericResponse.ok(
                FishResponseVM.fromFish(fishService.updateFish(id, fishRequestVM)),
                "Level updated successfully") ;
    }

    @DeleteMapping("/{id}")
    public void deleteFish(@PathVariable("id") Long id){
        fishService.deleteFish(id);
    }
}
