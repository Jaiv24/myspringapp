package com.example.myspringapp.resourse;

import com.example.myspringapp.model.Album;
import com.example.myspringapp.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AlbumResourse {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/album")
    public Album getAlbum(){
        return albumService.getAlbum();
    }
}