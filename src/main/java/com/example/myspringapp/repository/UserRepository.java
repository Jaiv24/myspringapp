package com.example.myspringapp.repository;

import com.example.myspringapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.*;


public interface UserRepository extends MongoRepository<User, String> {
    List<User> findUserByName(String iterable);
}
