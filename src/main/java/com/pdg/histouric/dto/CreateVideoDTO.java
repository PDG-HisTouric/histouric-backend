package com.pdg.histouric.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateVideoDTO {

    @NotNull(message = "Video uri cannot be null")
    @NotEmpty(message = "Video uri cannot be empty")
    @NotBlank(message = "Video uri cannot be blank")
    @Length(max = 1000, message = "Video uri characters long must be less than or equal to 1000")
    private String videoUri;

    @NotNull(message = "needsUrlGen cannot be null")
    private boolean needsUrlGen;
}
