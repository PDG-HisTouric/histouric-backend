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
public class ResponseRouteDTO {
    @NotNull(message = "Id cannot be null")
    private UUID id;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    @Length(max = 255, message = "Name must have a maximum of 255 characters")
    private String name;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @NotBlank(message = "Description cannot be blank")
    @Length(max = 3000, message = "Description must have a maximum of 3000 characters")
    private String description;

    @NotNull(message = "Owner id cannot be null")
    private ResponseUserDTO owner;

    @NotNull(message = "BIC list cannot be null")
    @NotEmpty(message = "BIC list cannot be empty")
    private List<ResponseBicDTO> bics;
}
