package com.example.myspringapp.repository;

import com.example.myspringapp.model.Album;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepository {
    public Album getAlbum() { return new Album(458, "Rav4", "Suv ...", "Rav4.jpg");}
}
