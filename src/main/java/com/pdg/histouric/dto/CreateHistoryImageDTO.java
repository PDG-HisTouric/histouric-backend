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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateHistoryImageDTO {

    @NotNull(message = "Image uri cannot be null")
    @NotEmpty(message = "Image uri cannot be empty")
    @NotBlank(message = "Image uri cannot be blank")
    @Length(max = 1000, message = "Image uri characters long must be less than or equal to 1000")
    private String imageUri;

    @NotNull(message = "Start time cannot be null")
    @Min(value = 0, message = "Start time must be greater than or equal to 0")
    private int startTime;

    @NotNull(message = "needsUrlGen cannot be null")
    private boolean needsUrlGen;
}
