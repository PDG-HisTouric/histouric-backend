package com.pdg.histouric.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.pdg.histouric.config.FirebaseProperties;
import com.pdg.histouric.service.FirebaseStorageService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    FirebaseProperties firebaseProperties;

    @EventListener
    public void init(ApplicationReadyEvent event) throws IOException {
        firebaseProperties.saveJson();

        InputStream serviceAccount;
        do {
            Thread.yield();
            serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-adminsdk.json");
        } while (serviceAccount == null);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(firebaseProperties.getBucketName())
                .build();

        if (FirebaseApp.getApps().isEmpty()) FirebaseApp.initializeApp(options);
    }

    @Override
    public List<String> uploadAudioData(MultipartFile[] audios) throws IOException {
        List<String> bucketNames = new ArrayList<>();
        for (MultipartFile audio : audios) {
            UUID uuid = UUID.randomUUID();
            String path = "audios/"+uuid+"_"+audio.getOriginalFilename();
            Bucket bucket = StorageClient.getInstance().bucket();
            String blobName = bucket.create(path, audio.getBytes(), audio.getContentType()).getName();
            bucketNames.add(blobName);
        }
        return bucketNames;
    }

    @Override
    public List<String> uploadVideoData(MultipartFile[] videos) throws IOException {
        List<String> bucketNames = new ArrayList<>();
        for (MultipartFile video : videos) {
            UUID uuid = UUID.randomUUID();
            String path = "videos/"+uuid+"_"+video.getOriginalFilename();
            Bucket bucket = StorageClient.getInstance().bucket();
            String blobName = bucket.create(path, video.getBytes(), video.getContentType()).getName();
            bucketNames.add(blobName);
        }
        return bucketNames;
    }

    @Override
    public List<String> uploadImageData(MultipartFile[] images) throws IOException {
        List<String> bucketNames = new ArrayList<>();
        for (MultipartFile image : images) {
            UUID uuid = UUID.randomUUID();
            String path = "images/"+uuid+"_"+image.getOriginalFilename();
            Bucket bucket = StorageClient.getInstance().bucket();
            String blobName = bucket.create(path, image.getBytes(), image.getContentType()).getName();
            bucketNames.add(blobName);
        }
        return bucketNames;
    }
}
