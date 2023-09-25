package com.pdg.histouric.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class TextDTO {
    private UUID textId;

    @NotNull(message = "Text cannot be null")
    @NotEmpty(message = "Text cannot be empty")
    @NotBlank(message = "Text cannot be blank")
    private String text;

    @NotNull(message = "Start time cannot be null")
    @Min(value = 0, message = "Start time must be greater than or equal to 0")
    private int startTime;
    private String historyId;
}
