package com.pdg.histouric.dto;

import jakarta.validation.constraints.Email;
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
public class ResponseUserDTO {
    private UUID id;

    private String token;

    @NotNull(message = "Nickname cannot be null")
    @NotEmpty(message = "Nickname cannot be empty")
    @NotBlank(message = "Nickname cannot be blank")
    @Length(max = 50, message = "Nickname must be 50 characters long")
    private String nickname;

    @NotNull(message = "User email cannot be null")
    @NotEmpty(message = "User email cannot be empty")
    @NotBlank(message = "User email cannot be blank")
    @Email(regexp = "\\w+\\.?\\w+@\\w+\\.\\w+", message = "User email must be a valid email address")
    private String email;

    private List<RoleDTO> roles;
}
