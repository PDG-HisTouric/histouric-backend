package com.pdg.histouric.firebase;

import com.pdg.histouric.dto.CreateHistoryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FirebaseStorageService {
    String uploadFile(MultipartFile file) throws IOException;
    void uploadHistoryData(CreateHistoryDTO createHistoryDTO) throws IOException;
}
