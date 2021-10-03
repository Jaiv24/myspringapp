package com.example.myspringapp.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Configuration
public class FirebaseConfig {
    public FirebaseApp initializeFirebase() throws IOException {



//        if(FirebaseApp.getInstance(FirebaseApp.DEFAULT_APP_NAME) != null) {
//            return FirebaseApp.getInstance().delete();
//        }
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:backend-spring21-firebase.json");

        FileInputStream serviceAccount =
                new FileInputStream(resource.getFile());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
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


        return FirebaseApp.initializeApp(options);

    }
}
