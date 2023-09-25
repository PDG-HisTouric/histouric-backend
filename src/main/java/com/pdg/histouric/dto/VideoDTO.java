package com.pdg.histouric.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class VideoDTO {
    private UUID id;

    @NotNull(message = "Video url cannot be null")
    @NotEmpty(message = "Video url cannot be empty")
    @NotBlank(message = "Video url cannot be blank")
    private String videoUrl;

    @NotNull(message = "Start time cannot be null")
    @Min(value = 0, message = "Start time must be greater than or equal to 0")
    private int startTime;

    private UUID historyId;
}
