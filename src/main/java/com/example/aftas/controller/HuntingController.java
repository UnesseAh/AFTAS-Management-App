package com.example.aftas.controller;

import com.example.aftas.DTO.HuntingDTO;
import com.example.aftas.controller.vm.HuntingResponseVM;
import com.example.aftas.entities.Hunting;
import com.example.aftas.handler.response.GenericResponse;
import com.example.aftas.service.interfaces.HuntingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/huntings")
public class HuntingController {

    private final HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @PostMapping
    public ResponseEntity createHunting(@RequestBody HuntingDTO huntingDTO){
        Hunting hunting = huntingService.createHunting(huntingDTO);
        HuntingResponseVM huntingResponseVM = HuntingResponseVM.fromHunting(hunting);
        return GenericResponse.created(huntingResponseVM, "Hunting saved successfully");
    }
}
