package com.example.aftas.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.stream.Collectors;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> dataNotValid(MethodArgumentNotValidException ex){
        HashMap<String, String> errorsMessage = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> errorsMessage.put(error.getField(),error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorsMessage, HttpStatus.BAD_REQUEST);
    };
}
