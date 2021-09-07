package com.example.myspringapp.exception;

public class InvalidTokenException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid idToken Exception";
    }
}
