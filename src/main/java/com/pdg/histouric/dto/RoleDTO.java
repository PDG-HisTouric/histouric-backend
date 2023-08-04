package com.pdg.histouric.dto;

import jakarta.validation.constraints.*;
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
public class RoleDTO {

    private UUID id;

    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be empty")
    @NotBlank(message = "Role name cannot be blank")
    @Length(max = 50, message = "Role name must be 50 characters long")
    private String name;

    @NotEmpty(message = "Role description cannot be empty")
    @NotBlank(message = "Role description cannot be blank")
    @Length(max = 255, message = "Role description must be 255 characters long")
    private String description;

    private List<PermissionDTO> permissions;
}
