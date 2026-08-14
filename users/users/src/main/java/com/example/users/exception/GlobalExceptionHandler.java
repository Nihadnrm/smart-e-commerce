package com.example.users.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFound.class)
    public Map<String, String> handleUserNotFound(UserNotFound ex) {
        Map<String,String>error= new HashMap<>();
        error.put("message", ex.getMessage());
        return error;
    }

   @ExceptionHandler(DuplicateUser.class)
   public Map<String,String>handleDuplicateUser(DuplicateUser ex){
    Map<String,String>error=new HashMap<>();
    error.put("message",ex.getMessage());
    return error;

   }

@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String,String>handleValidationExceptions(MethodArgumentNotValidException ex){
    Map<String,String>error=new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(er->{
        error.put(er.getField(),er.getDefaultMessage());
    });
    return error;


}


}
