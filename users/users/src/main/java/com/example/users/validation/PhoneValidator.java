package com.example.users.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone,String> {

    @Override
    public boolean isValid(String value,ConstraintValidatorContext context){
          if(value==null){
            return false;
          }
          return value.matches("^[6-9][0-9]{9}$");
    }

}
