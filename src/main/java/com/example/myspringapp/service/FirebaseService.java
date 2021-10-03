package com.example.myspringapp.service;


import com.example.myspringapp.config.FirebaseConfig;
import com.example.myspringapp.model.FirebaseUser;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FirebaseService {
    @Autowired
    private FirebaseConfig firebaseConfig;

    public FirebaseUser authenticate(String idToken) throws IOException, FirebaseAuthException {
        //        FirebaseApp firebaseApp = null;
//        List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
//        if(firebaseApps!=null && !firebaseApps.isEmpty()){
//            for(FirebaseApp app : firebaseApps){
//                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
//                    firebaseApp = app;
//            }
//        }
//        else
//            firebaseApp = FirebaseApp.initializeApp(options);
        FirebaseApp firebaseApp = firebaseConfig.initializeFirebase();
        FirebaseToken firebaseToken = FirebaseAuth.getInstance(firebaseApp).verifyIdToken(idToken);

        String uid = firebaseToken.getUid(); // Auto-generated and maintained by Firebase
        String name = firebaseToken.getName(); //Usually this is NULL, since we did not get this during SignUp
        String email = firebaseToken.getEmail(); // This is unique and is a mandatory for Google Firebase IAM

        return new FirebaseUser(uid,name,email);
    }
}
