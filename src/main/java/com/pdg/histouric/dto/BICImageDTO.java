package com.pdg.histouric.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BICImageDTO {

    @NotNull(message = "The image uri cannot be null")
    @NotEmpty(message = "The image uri cannot be empty")
    @NotBlank(message = "The image uri cannot be blank")
    @Length(max = 255, message = "The image uri must have a maximum of 255 characters")
    private String imageUri;

}
