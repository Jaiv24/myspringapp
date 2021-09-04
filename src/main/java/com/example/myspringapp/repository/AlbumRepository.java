package com.example.myspringapp.repository;

import com.example.myspringapp.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
}
