package com.example.myspringapp.repository;

import com.example.myspringapp.model.Album;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AlbumRepository {
    List<Album> albums = new ArrayList<>();
    public Album getAlbum() {
        return new Album("Rav4", "Suv ...", "Rav4.jpg");
    }

    public Album saveAlbum(Album album) {
        album.setAlbumId(albums.size() + 1);
        albums.add(album);
        return album;
    }

    public List<Album> getAllAlbum() {
        return albums;
    }

    public Album getAlbumById(int albumId) {

        for(Album album: albums){
            if(album.getAlbumId() == albumId){
                return album;
            }
        }
        return null;
    }

    public Album updateAlbum(int albumId, Album album) {
        for(Album album1: albums){
            if(album1.getAlbumId() == albumId){
                album1.setName(album.getName());
                album1.setDescription(album.getDescription());
                album1.setCoverPicUrl(album.getCoverPicUrl());
                return album1;
            }
        }
        return null;
    }

    public Album deleteAlbum(int albumId) {
        Album deletedAlbum = null;
        for(Album album : albums){
            if(album.getAlbumId() == albumId){
                deletedAlbum = album;
                albums.remove(album);
                return deletedAlbum;
            }
        }
        return null;
    }
}
