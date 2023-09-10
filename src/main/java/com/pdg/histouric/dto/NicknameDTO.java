package com.pdg.histouric.dto;

import com.pdg.histouric.model.BIC;
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
public class NicknameDTO {

    private UUID id;

    @NotNull(message = "Nickname cannot be null")
    @NotEmpty(message = "Nickname cannot be empty")
    @NotBlank(message = "Nickname cannot be blank")
    @Length(max = 255, message = "Nickname must be 255 characters long")
    private String nickname;

    private BicDTO bicDTO;
}
