package com.pdg.histouric.firebase;

import com.pdg.histouric.api.FirebaseStorageAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class FirebaseStorageController implements FirebaseStorageAPI {
    private final FirebaseStorageService firebaseStorageService;

    @Override
    public List<String> uploadImages(MultipartFile[] multipartFiles) throws IOException {
        return firebaseStorageService.uploadImageData(multipartFiles);
    }

    @Override
    public List<String> uploadVideos(MultipartFile[] multipartFiles) throws IOException {
        return firebaseStorageService.uploadVideoData(multipartFiles);
    }

    @Override
    public List<String> uploadAudios(MultipartFile[] multipartFiles) throws IOException {
        return firebaseStorageService.uploadAudioData(multipartFiles);
    }
}