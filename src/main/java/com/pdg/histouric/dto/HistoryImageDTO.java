package com.pdg.histouric.dto;

import com.pdg.histouric.model.History;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public class HistoryImageDTO {

    private String imageId;

    @NotNull(message = "URI cannot be null")
    @NotEmpty(message = "URI cannot be empty")
    @NotBlank(message = "URI cannot be blank")
    @Length(max = 255, message = "URI must be 255 characters long")
    private String imageUri;

    @NotNull(message = "Start time cannot be null")
    @Min(value = 0, message = "Start time must be greater than or equal to 0")
    private int startTime;
    private UUID historyId;
}
