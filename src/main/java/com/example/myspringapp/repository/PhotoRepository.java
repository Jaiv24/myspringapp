package com.example.myspringapp.repository;

import com.example.myspringapp.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhotoRepository extends MongoRepository<Photo, String> {
    List<Photo> findByAlbumId(String albumId);
}
