package com.example.myspringapp.service;

import com.example.myspringapp.model.Photo;
import com.example.myspringapp.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo savePhoto(Photo photo){
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo updatePhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public String deletePhoto(String photoId) {
        photoRepository.deleteById(photoId);
        return photoId;
    }

    public Photo getPhotoById(String photoId) {
        return photoRepository.findById(photoId).get();
    }
}
