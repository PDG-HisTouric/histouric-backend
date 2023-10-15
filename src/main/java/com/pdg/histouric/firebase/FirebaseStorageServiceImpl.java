package com.pdg.histouric.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    FirebaseProperties firebaseProperties;

    @EventListener
    public void init(ApplicationReadyEvent event) throws IOException {
        firebaseProperties.saveJson();

        InputStream serviceAccount;
        do {
            serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-adminsdk.json");
            Thread.yield();
        } while (serviceAccount == null);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(firebaseProperties.getBucketName())
                .build();

        if (FirebaseApp.getApps().isEmpty()) FirebaseApp.initializeApp(options);
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();
        bucket.create(file.getOriginalFilename(), file.getBytes(), file.getContentType());
        return file.getOriginalFilename();
    }
}
