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
public class PermissionDTO {

    private UUID id;

    @NotNull(message = "Permission name cannot be null")
    @NotEmpty(message = "Permission name cannot be empty")
    @NotBlank(message = "Permission name cannot be blank")
    @Length(max = 50, message = "Permission name must be 50 characters long")
    private String name;

    @NotNull(message = "Create permission cannot be null")
    private boolean create;

    @NotNull(message = "Read permission cannot be null")
    private boolean read;

    @NotNull(message = "Update permission cannot be null")
    private boolean update;

    @NotNull(message = "Delete permission cannot be null")
    private boolean delete;

    private List<RoleDTO> roles;
}
