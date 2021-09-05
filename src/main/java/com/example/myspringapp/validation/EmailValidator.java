package com.example.myspringapp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidateEmail, String> {

    private String customRegex;

    @Override
    public void initialize(ValidateEmail constraintAnnotation) {
        customRegex = constraintAnnotation.customRegex();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(customRegex);
    }
}
