package com.pdg.histouric.firebase;

import com.pdg.histouric.dto.CreateHistoryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FirebaseStorageService {
    List<String> uploadAudioData(MultipartFile[] audios) throws IOException;
    List<String> uploadVideoData(MultipartFile[] videos) throws IOException;
    List<String> uploadImageData(MultipartFile[] images) throws IOException;
}
