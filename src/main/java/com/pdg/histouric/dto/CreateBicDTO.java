package com.pdg.histouric.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBicDTO {

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    @Length(max = 255, message = "Name must be 255 characters long")
    private String name;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    @Length(max = 255, message = "Description must be 255 characters long")
    private String description;

    @NotNull(message = "Latitude cannot be null")
    @Digits(integer = 3, fraction = 16, message = "Latitude must be a valid latitude")
    private double latitude;

    @NotNull(message = "Longitude cannot be null")
    @Digits(integer = 3, fraction = 16, message = "Longitude must be a valid longitude")
    private double longitude;

    @NotNull(message = "Existence specification cannot be null")
    private boolean existss;

    private List<NicknameDTO> nicknames;

    private List<BICImageDTO> imagesUris;

    private List<String> historiesIds;
    
}
