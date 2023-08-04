package com.pdg.histouric.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @NotBlank(message = "Username cannot be blank")
    @Length(max = 50, message = "Username must be 50 characters long")
    private String username;

    @NotNull(message = "User email cannot be null")
    @NotEmpty(message = "User email cannot be empty")
    @NotBlank(message = "User email cannot be blank")
    @Email(regexp = "\\w+\\.?\\w+@\\w+\\.\\w+", message = "User email must be a valid email address")
    private String email;

    @NotNull(message = "User password cannot be null")
    @NotEmpty(message = "User password cannot be empty")
    @NotBlank(message = "User password cannot be blank")
    @Length(max = 255, message = "User password must be 255 characters long")
    private String password;

    private List<RoleDTO> roles;
}
