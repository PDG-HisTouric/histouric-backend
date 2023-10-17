package com.pdg.histouric.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/firebase-storage")
public interface FirebaseStorageAPI {
    String ROOT_PATH = "/api/v1/firebase-storage";

    @PostMapping("/images")
    List<String> uploadImages(@RequestParam(name = "files") MultipartFile[] multipartFiles) throws IOException;

    @PostMapping("/videos")
    List<String> uploadVideos(@RequestParam(name = "files") MultipartFile[] multipartFiles) throws IOException;

    @PostMapping("/audios")
    List<String> uploadAudios(@RequestParam(name = "files") MultipartFile[] multipartFiles) throws IOException;
}
