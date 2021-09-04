package com.example.myspringapp.repository;

import com.example.myspringapp.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumRepository {
    List<Album> albums = new ArrayList<>();
    public Album getAlbum() { return new Album(458, "Rav4", "Suv ...", "Rav4.jpg");}

    public Album saveAlbum(Album album) {
        albums.add(album);
        return album;
    }
}
