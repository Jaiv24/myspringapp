package com.example.myspringapp.model;

import com.example.myspringapp.validation.ValidateEmail;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;

public class User {


    @Id
    private String id;
    @NotEmpty
    private String name;
    @ValidateEmail(customRegex = "^[a-zA-Z0-9._]+@{1}[a-zA-Z0-9_]+[.]{1}[a-zA-Z0-9_]+[a-zA-Z0-9._]+$")
    @NotEmpty(message = "email Id cannot be null")
    private String email;
    @NotEmpty(message = "Profile Picture Id cannot be null")
    private String profilePicUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String name, String email, String profilePicUrl) {
        this.name = name;
        this.email = email;
        this.profilePicUrl = profilePicUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
