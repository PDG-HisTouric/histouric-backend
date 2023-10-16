package com.pdg.histouric.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CreateHistoryDTO {

    private UUID id;

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be blank")
    @Length(max = 255, message = "Title can have a maximum of 255 characters")
    private String title;

    @NotNull(message = "Audio cannot be null")
    private CreateAudioDTO createAudioDTO;

    @NotNull(message = "Owner UUID cannot be null")
    private UUID owner;

    private List<CreateVideoDTO> videos;

    @NotNull(message = "Texts cannot be null")
    @NotEmpty(message = "Texts cannot be empty")
    private List<TextDTO> texts;

    private List<CreateHistoryImageDTO> images;

    public void validate() {
        createAudioDTO.validate();
        videos.forEach(CreateVideoDTO::validate);
        images.forEach(CreateHistoryImageDTO::validate);
    }
}
