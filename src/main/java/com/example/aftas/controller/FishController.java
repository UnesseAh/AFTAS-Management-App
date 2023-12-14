package com.example.aftas.controller;

import com.example.aftas.DTO.FishDTO;
import com.example.aftas.controller.vm.FishResponseVM;
import com.example.aftas.controller.vm.LevelResponseVM;
import com.example.aftas.entities.Fish;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.interfaces.FishService;
import com.example.aftas.service.interfaces.LevelService;
import jakarta.validation.Valid;
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
    public ResponseEntity createFish(@RequestBody @Valid FishDTO fishDTO){
        Fish fish = fishService.createFish(fishDTO);
        return ResponseMessage.created(FishResponseVM.fromFish(fish), "Fish created successfully");
    }

    @GetMapping
    public ResponseEntity getAllFish(){
        List<Fish> fishes = fishService.getAllFishes();
        if (fishes.isEmpty()){
            return ResponseMessage.notFound("No fishes were found");
        }
        return ResponseMessage.ok(fishes.stream().map(FishResponseVM::fromFish).toList(), "Fishes retrieved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFish(@PathVariable("id") Long id, @RequestBody FishDTO fishDTO){

        return ResponseMessage.ok(
                FishResponseVM.fromFish(fishService.updateFish(id, fishDTO)),
                "Level updated successfully") ;
    }

    @DeleteMapping("/{id}")
    public void deleteFish(@PathVariable("id") Long id){
        fishService.deleteFish(id);
    }
}
