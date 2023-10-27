package com.pdg.histouric.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.pdg.histouric.config.FirebaseProperties;
import com.pdg.histouric.model.History;
import com.pdg.histouric.service.FirebaseStorageService;
import com.pdg.histouric.utils.FirebaseUrl;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    FirebaseProperties firebaseProperties;
    Map<String, FirebaseUrl> urlMap;

    public FirebaseStorageServiceImpl(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
        urlMap = new HashMap<>();
    }

    @EventListener
    public void init(ApplicationReadyEvent event) throws IOException {
        File file = firebaseProperties.saveJson();

        InputStream serviceAccount;
        serviceAccount = new FileInputStream(file);
        System.out.println("Firebase json file loaded successfully");
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

    @Override
    public String getSignedUrl(String fileName, TimeUnit timeUnit, long duration) {
        if (urlMap.containsKey(fileName)) {
            return urlMap.get(fileName).getUrl().orElseGet(() -> generateSignedUrl(fileName, timeUnit, duration));
        }
        return generateSignedUrl(fileName, timeUnit, duration);
    }

    private String generateSignedUrl(String fileName, TimeUnit timeUnit, long duration) {
        String url = StorageClient.getInstance().bucket().get(fileName).signUrl(duration, timeUnit).toString();
        FirebaseUrl firebaseUrl = FirebaseUrl.builder()
                .expiration(new Date(System.currentTimeMillis() + timeUnit.toMillis(duration)))
                .url(url)
                .build();
        urlMap.put(fileName, firebaseUrl);
        return url;
    }

    @Override
    public History putUrlsToHistory(History history) {
        history.getImages().forEach(image -> {
            if (!image.isNeedsUrlGen()) return;
            image.setImageUri(getSignedUrl(image.getImageUri(), TimeUnit.DAYS, 1));
        });
        history.getVideos().forEach(video -> {
            if (!video.isNeedsUrlGen()) return;
            video.setVideoUri(getSignedUrl(video.getVideoUri(), TimeUnit.DAYS, 1));
        });
        if (history.getAudio().isNeedsUrlGen()) {
            history.getAudio().setAudioUri(getSignedUrl(history.getAudio().getAudioUri(), TimeUnit.DAYS, 1));
        }
        return history;
    }
}
