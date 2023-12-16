package com.example.aftas.handler.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class GenericResponse {
    private final int statusCode;
    private final String timeStamp;
    private final String message;
    private Object data;

    public GenericResponse(int statusCode, Object data, String message){
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.data = data;
        this.message = message;
    }

    public GenericResponse(int statusCode, String message){
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.message = message;
    }

    public static ResponseEntity<?> ok(Object data, String message){
        return new ResponseEntity<>(new GenericResponse(HttpStatus.OK.value(),data, message),HttpStatus.OK);
    }

    public static ResponseEntity<?> created(Object data, String message){
        return new ResponseEntity<>(new GenericResponse(HttpStatus.CREATED.value(), data , message), HttpStatus.CREATED);
    }

    public static ResponseEntity<?> notFound(String message){
        return new ResponseEntity<>(new GenericResponse(HttpStatus.NOT_FOUND.value(), message),HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<?> badRequest(String message){
        return new ResponseEntity<>(new GenericResponse(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }

}
