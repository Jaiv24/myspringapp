package com.example.myspringapp.exception;

public class NameNotAllowedException extends Exception{

    @Override
    public String getMessage(){
        return "root not allowed";
    }
}
