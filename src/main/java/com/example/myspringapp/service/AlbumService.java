package com.example.myspringapp.service;

import com.example.myspringapp.model.Album;
import com.example.myspringapp.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    public Album getAlbum(){ return albumRepository.getAlbum(); }

    public Album saveAlbum(Album album) {
        return albumRepository.saveAlbum(album);
    }
}
