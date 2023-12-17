package com.example.aftas.controller;

import com.example.aftas.controller.vm.Hunting.HuntingRequestVm;
import com.example.aftas.controller.vm.Hunting.HuntingResponseVM;
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
    public ResponseEntity<?> createHunting(@RequestBody HuntingRequestVm huntingRequestVm){
        Hunting hunting = huntingRequestVm.toHunting();
        Long weight = huntingRequestVm.weight();
        Hunting hunt = huntingService.createHunting(hunting, weight);
        HuntingResponseVM responseVM = HuntingResponseVM.fromHunting(hunt);
        return GenericResponse.created(responseVM, "Hunting created successfully");
    }
}
