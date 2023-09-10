package com.pdg.histouric.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class HistouricImageDTO {

    private String imageId;

    @NotNull(message = "URI cannot be null")
    @NotEmpty(message = "URI cannot be empty")
    @NotBlank(message = "URI cannot be blank")
    @Length(max = 255, message = "URI must be 255 characters long")
    private String imageUri;

    @NotNull(message = "Start time cannot be null")
    @NotEmpty(message = "Start time cannot be empty")
    @NotBlank(message = "Start time cannot be blank")
    private int startTime;

    private BicDTO bicDTO;
}
