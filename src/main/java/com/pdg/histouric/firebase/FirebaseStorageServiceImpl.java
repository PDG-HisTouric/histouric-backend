package com.pdg.histouric.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.pdg.histouric.dto.CreateAudioDTO;
import com.pdg.histouric.dto.CreateHistoryDTO;
import com.pdg.histouric.dto.CreateHistoryImageDTO;
import com.pdg.histouric.dto.CreateVideoDTO;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
        String blobName = bucket.create("prueba/"+file.getOriginalFilename(), file.getBytes(), file.getContentType()).getName();
        Blob blob = bucket.get(blobName);
        String url = blob.signUrl(1, java.util.concurrent.TimeUnit.DAYS).toString();
        return file.getOriginalFilename();
    }

    @Override
    public void uploadHistoryData(CreateHistoryDTO createHistoryDTO) throws IOException {
        for (int i = 0; i < createHistoryDTO.getImages().size(); i++) {
            CreateHistoryImageDTO image = createHistoryDTO.getImages().get(i);
            if (!image.isNeedsUrlGen()) continue;
            UUID uuid = UUID.randomUUID();
            String path = "images/"+uuid+"_"+image.getImageFile().getOriginalFilename();
            Bucket bucket = StorageClient.getInstance().bucket();
            String blobName = bucket.create(path, image.getImageFile().getBytes(), image.getImageFile().getContentType())
                    .getName();
            image.setImageUri(blobName);
        }

        for (int i = 0; i < createHistoryDTO.getVideos().size(); i++) {
            CreateVideoDTO video = createHistoryDTO.getVideos().get(i);
            if (!video.isNeedsUrlGen()) continue;
            UUID uuid = UUID.randomUUID();
            String path = "videos/"+uuid+"_"+video.getVideoFile().getOriginalFilename();
            Bucket bucket = StorageClient.getInstance().bucket();
            String blobName = bucket.create(path, video.getVideoFile().getBytes(), video.getVideoFile().getContentType())
                    .getName();
            video.setVideoUri(blobName);
        }

        CreateAudioDTO audio = createHistoryDTO.getCreateAudioDTO();
        if (audio.isNeedsUrlGen()) {
            UUID uuid = UUID.randomUUID();
            String path = "audios/"+uuid+"_"+audio.getAudioFile().getOriginalFilename();
            Bucket bucket = StorageClient.getInstance().bucket();
            String blobName = bucket.create(path, audio.getAudioFile().getBytes(), audio.getAudioFile().getContentType())
                    .getName();
            audio.setAudioUri(blobName);
        }
    }
}
