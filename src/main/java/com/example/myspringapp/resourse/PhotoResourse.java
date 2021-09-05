package com.example.myspringapp.resourse;


import com.example.myspringapp.model.Album;
import com.example.myspringapp.model.Photo;
import com.example.myspringapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoResourse {

    @Autowired
    private PhotoService photoService;

    @PostMapping
    public Photo savePhoto(@RequestBody Photo photo){return photoService.savePhoto(photo);}

    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @PutMapping
    public Photo updatePhoto(@RequestBody Photo photo){
        return photoService.updatePhoto(photo);
    }

    @DeleteMapping("/photo")
    public void deletePhoto(@RequestParam(name = "photoId") String photoId){
        photoService.deletePhoto(photoId);
    }
}
