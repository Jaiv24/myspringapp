package com.example.myspringapp.repository;

import com.example.myspringapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();

    public User getUser(){
        return new User("Jaiv", "jaiv.maurya@gmail.com", "jaiv.jpg");
    }

    public User deleteUser(int userId) {
        User deletedUser = null;
        for(User user : users){
            if(user.getUserId() == userId){
                deletedUser = user;
                users.remove(user);
                return deletedUser;
            }
        }
        return null;
    }

    public List<User> getAllUser() {
        return users;
    }

    public User getUserById(int userId) {
        for(User user : users){
            if(user.getUserId() == userId){
                return user;
            }
        }
        return null;
    }

    public User updateUser(int userId, User user) {
        for(User u : users){
            if(u.getUserId() == userId){
                u.setEmail(user.getEmail());
                u.setName(user.getName());
                u.setProfilePicUrl(user.getProfilePicUrl());
                return u;
            }
        }
        return null;
    }

    public User saveUser(User user) {
        user.setUserId(users.size() + 1);
        users.add(user);
        return user;
    }
}
