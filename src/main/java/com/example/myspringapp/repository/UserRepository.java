package com.example.myspringapp.repository;

import com.example.myspringapp.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public User getUser(){
        return new User("Jaiv", "16 Gunton Dr", 21, "jaiv.jpg");
    }
}
