package com.example.myspringapp.repository;

import com.example.myspringapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

}
