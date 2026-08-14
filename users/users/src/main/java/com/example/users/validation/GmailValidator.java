package com.example.users.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<Gmail,String> {
    @Override
    public boolean isValid(String value,ConstraintValidatorContext context){
         System.out.println("Validator Running: " + value);
          if(value==null){
            return false;
          }
          return value.endsWith("@gmail.com");
    }

}
