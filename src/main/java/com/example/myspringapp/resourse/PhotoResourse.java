package com.example.myspringapp.resourse;


import com.example.myspringapp.exception.InvalidTokenException;
import com.example.myspringapp.exception.UserNotAuthorizedException;
import com.example.myspringapp.model.Album;
import com.example.myspringapp.model.FirebaseUser;
import com.example.myspringapp.model.Photo;
import com.example.myspringapp.service.FirebaseService;
import com.example.myspringapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
public class PhotoResourse {

    @Autowired
    private PhotoService photoService;

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @PostMapping
    public Photo savePhoto(@RequestBody @Valid Photo photo,
                           @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {

            photo.setCreatedBy(firebaseUser.getEmail());

            if (photo.getDateCreated() == null) {
                photo.setDateCreated(java.time.LocalDate.now());
            }
            return photoService.savePhoto(photo);
        } else {
            throw new InvalidTokenException();
        }
    }

    @PutMapping
    public Photo updatePhoto(@RequestBody @Valid Photo photo,
                             @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(photo.getCreatedBy())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "Photo.createdBy: " + photo.getCreatedBy()
                );
            } else {
                return photoService.updatePhoto(photo);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    @DeleteMapping
    public String deletePhoto(@RequestParam(name = "photoId") String photoId,
                              @RequestHeader(name = "idToken") String idToken)
            throws
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        Photo photo = photoService.getPhotoById(photoId);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(photo.getCreatedBy())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "Photo.createdBy: " + photo.getCreatedBy()
                );
            } else {
                return photoService.deletePhoto(photoId);
            }
        } else {
            throw new InvalidTokenException();
        }
    }
}
