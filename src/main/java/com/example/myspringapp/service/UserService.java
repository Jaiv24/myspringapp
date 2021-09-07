package com.example.myspringapp.service;

import com.example.myspringapp.model.User;
import com.example.myspringapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "User Deleted";
    }

    public List<User> getUserByName(String name){
        return userRepository.findUserByName(name);
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).get();
    }
}
