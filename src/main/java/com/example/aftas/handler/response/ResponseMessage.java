package com.example.aftas.handler.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ResponseMessage {
    private int statusCode;
    private String timeStamp;
    private String message;
    private Object data;

    public ResponseMessage(int statusCode, Object data, String message){
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.data = data;
        this.message = message;
    }

    public ResponseMessage (int statusCode, String message){
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.message = message;
    }

    public static ResponseEntity ok(Object data, String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.OK.value(), data, message),HttpStatus.OK);
    }
    public static ResponseEntity created(Object data, String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.CREATED.value(), data, message), HttpStatus.CREATED);
    }
    public static ResponseEntity notFound(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.NOT_FOUND.value(), message),HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity badRequest(String message){
        return new ResponseEntity<>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }

}
