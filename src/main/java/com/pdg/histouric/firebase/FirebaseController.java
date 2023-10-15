package com.pdg.histouric.firebase;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/firebase")
public class FirebaseController {
    private final FirebaseStorageService firebaseStorageService;

    @PostMapping
    public String uploadFile(@RequestParam(name = "file") MultipartFile file) throws IOException {
        return firebaseStorageService.uploadFile(file);
    }
}