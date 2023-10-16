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

    private String audioUri;

    private MultipartFile audioFile;

    @NotNull(message = "Start time cannot be null")
    @Min(value = 0, message = "Start time must be greater than or equal to 0")
    private int startTime;

    @NotNull(message = "needsUrlGen cannot be null")
    private boolean needsUrlGen;

    private UUID historyId;
}
