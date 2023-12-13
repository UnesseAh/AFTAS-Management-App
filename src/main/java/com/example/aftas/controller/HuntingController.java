package com.example.aftas.controller;

import com.example.aftas.controller.vm.HuntingRequestVM;
import com.example.aftas.entities.Hunting;
import com.example.aftas.service.interfaces.HuntingService;
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
    public Hunting createHunting(@RequestBody HuntingRequestVM huntingRequestVM){
//        Hunting hunting = huntingRequestVM.toHunting();
//
//        return huntingService.createHunting(huntingRequestVM);
        return null;
    }
}
