package com.example.myspringapp.service;

import com.example.myspringapp.model.Album;
import com.example.myspringapp.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }


    public Album updateAlbum(Album album) {
        return albumRepository.save(album);
    }

    public String deleteAlbum(String albumId) {
        albumRepository.deleteById(albumId);
        return albumId;
    }

    public Album getAlbumById(String albumId) {
        return albumRepository.findById(albumId).get();
    }

}

