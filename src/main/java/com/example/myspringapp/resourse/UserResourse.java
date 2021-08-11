package com.example.myspringapp.resourse;

import com.example.myspringapp.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserResourse {

    @GetMapping("/user")
    public User getUser(){
        return new User("Jaiv", "16 Gunton Dr", 21, "jaiv.jpg");
    }
}
