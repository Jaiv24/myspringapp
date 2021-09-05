package com.example.myspringapp.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidateEmail {
    String message() default "Invalid email address - email should contain only: alphabets, digits, '_' , '.' , '@'";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};

    String customRegex() default "[a-z]+";
}