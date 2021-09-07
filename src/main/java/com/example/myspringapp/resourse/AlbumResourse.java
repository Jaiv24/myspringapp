package com.example.myspringapp.resourse;

import com.example.myspringapp.exception.InvalidTokenException;
import com.example.myspringapp.exception.UserNotAuthorizedException;
import com.example.myspringapp.model.Album;
import com.example.myspringapp.model.FirebaseUser;
import com.example.myspringapp.service.AlbumService;
import com.example.myspringapp.service.FirebaseService;
import com.example.myspringapp.service.PhotoService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumResourse {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private FirebaseService firebaseService;

    private String createdBy;

    @PostMapping
    public Album saveAlbum(@RequestBody @Valid Album album,
                           @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            album.setCreatedBy(firebaseUser.getEmail());
            if (album.getDateCreated() == null) {
                album.setDateCreated(java.time.LocalDate.now());
            }
            album.setAlbumId(null);
            return albumService.saveAlbum(album);
        } else {
            throw new InvalidTokenException();
        }
    }

    @GetMapping
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @PutMapping
    public Album updateAlbum(@RequestBody @Valid Album album,
                             @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(album.getCreatedBy())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "Album.createdBy: " + album.getCreatedBy()
                );
            } else {
                return albumService.updateAlbum(album);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    @DeleteMapping
    public String deleteAlbum(@RequestParam(name = "albumId") String albumId,
                              @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        Album album = albumService.getAlbumById(albumId);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(album.getCreatedBy())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "Album.createdBy: " + album.getCreatedBy()
                );
            } else {
                return albumService.deleteAlbum(albumId);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

}
