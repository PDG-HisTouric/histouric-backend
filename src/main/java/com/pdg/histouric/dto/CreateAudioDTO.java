package com.pdg.histouric.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAudioDTO {

    private String audioUri;

    @NotNull(message = "needsUrlGen cannot be null")
    private boolean needsUrlGen;

    public void validate() {
        if (needsUrlGen && audioUri != null) {
            throw new IllegalArgumentException("El uri del audio debe ser nulo si needUrlGen es verdadero");
        }
        if (!needsUrlGen && audioUri == null) {
            throw new IllegalArgumentException("El uri del audio no puede ser nulo si needUrlGen es falso");
        }
    }
}
