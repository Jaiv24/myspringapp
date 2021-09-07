package com.example.myspringapp.resourse;


import com.example.myspringapp.exception.InvalidTokenException;
import com.example.myspringapp.exception.NameNotAllowedException;
import com.example.myspringapp.exception.UserNotAuthorizedException;
import com.example.myspringapp.model.FirebaseUser;
import com.example.myspringapp.model.User;
import com.example.myspringapp.service.FirebaseService;
import com.example.myspringapp.service.UserService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserResourse {

    @Autowired
    private UserService userService;

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping
    public User saveUser(@RequestBody @Valid  User user, @RequestHeader(name = "idToken") String idToken) throws IOException, FirebaseAuthException, NameNotAllowedException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if(firebaseUser != null){
            if (user.getName().equalsIgnoreCase("root")) {
                throw new NameNotAllowedException();
            }
            user.setEmail(firebaseUser.getEmail());
            return userService.saveUser(user);
        } else {
            throw new IOException();
        }

    }

    @GetMapping("/all")
    public List<User> getAllUsers(@RequestHeader(name = "idToken") String idToken)
            throws FirebaseAuthException,
             IOException {
        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);
        if (firebaseUser != null) {
            return userService.getAllUsers();
        }

        return null;
    }
    @GetMapping("/find-by-name")
    public List<User> getUserByName(@RequestParam(name = "name") String name) throws NameNotAllowedException {
        if(name.equalsIgnoreCase("root")){
            throw new NameNotAllowedException();
        }
        return userService.getUserByName(name);
    }


    @PutMapping
    public User updateUser(@RequestBody @Valid User user,
                           @RequestHeader(name = "idToken") String idToken)
            throws NameNotAllowedException,
            IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        if (user.getName().equalsIgnoreCase("root")) {
            throw new NameNotAllowedException();
        }

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "User.email: " + user.getEmail()
                );
            } else {
                return userService.updateUser(user);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

    @DeleteMapping
    public String deleteUser(@RequestParam(name = "userId") String userId,
                             @RequestHeader(name = "idToken") String idToken)
            throws IOException,
            FirebaseAuthException,
            InvalidTokenException,
            UserNotAuthorizedException {

        User user = userService.getUserById(userId);

        FirebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            if (!firebaseUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new UserNotAuthorizedException(
                        "FireBaseUser.email: " + firebaseUser.getEmail() + "\n" +
                                "User.email: " + user.getEmail()
                );
            } else {
                return userService.deleteUser(userId);
            }
        } else {
            throw new InvalidTokenException();
        }
    }

}
