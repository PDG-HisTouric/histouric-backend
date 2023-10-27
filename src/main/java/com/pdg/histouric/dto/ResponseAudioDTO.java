package com.pdg.histouric.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAudioDTO {
    @NotNull(message = "Id cannot be null")
    private String id;

    @NotNull(message = "Id cannot be null")
    private String audioUri;

    @NotNull(message = "needsUrlGen cannot be null")
    private boolean needsUrlGen;

    private UUID historyId;
}
