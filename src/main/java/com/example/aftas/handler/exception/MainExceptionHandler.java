package com.example.aftas.handler.exception;

import com.example.aftas.handler.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.stream.Collectors;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFoundException(ResourceNotFoundException ex){
        HashMap<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getMessage());
        return new  ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity argumentNotValid(IllegalArgumentException ex){
        HashMap<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseMessage> globalExceptionHandler(Exception ex){
//        ResponseMessage message = new ResponseMessage(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                ex.getMessage());
//        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
