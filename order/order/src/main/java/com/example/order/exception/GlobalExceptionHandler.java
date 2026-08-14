package com.example.order.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(MethodArgumentNotValidException.class)
public Map<String,String>handleValidationExceptions(MethodArgumentNotValidException ex){
    Map<String,String>error=new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(er->{
        error.put(er.getField(),er.getDefaultMessage());
    });
    return error;
}

}
