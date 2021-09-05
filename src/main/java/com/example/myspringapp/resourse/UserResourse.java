package com.example.myspringapp.resourse;


import com.example.myspringapp.exception.NameNotAllowedException;
import com.example.myspringapp.model.User;
import com.example.myspringapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserResourse {

    @Autowired
    private UserService userService;

    @PostMapping
    public User saveUser(@RequestBody @Valid  User user){
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/find-by-name")
    public List<User> getUserByName(@RequestParam(name = "name") String name) throws NameNotAllowedException {
        if(name.equalsIgnoreCase("root")){
            throw new NameNotAllowedException();
        }
        return userService.getUserByName(name);
    }


    @PutMapping
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam(name = "userId") String userId){
        userService.deleteUser(userId);
    }

}
