package com.example.myspringapp.exception;

public class UserNotAuthorizedException extends Exception{
    private String CustomString;

    public UserNotAuthorizedException(){
        this.CustomString = "";
    }
    public UserNotAuthorizedException(String customString) {
        this.CustomString = customString;
    }

    public String getCustomString() {
        return CustomString;
    }

    public void setCustomString(String customString) {
        this.CustomString = customString;
    }

    @Override
    public String getMessage() {
        return "User not authorized for this operation. \n" + this.CustomString;
    }
}
