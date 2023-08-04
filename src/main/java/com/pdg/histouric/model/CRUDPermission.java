package com.pdg.histouric.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class CRUDPermission {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @NotNull(message = "Role id cannot be null")
    @Column(name = "permission_id", nullable = false, unique = true)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotNull(message = "Permission name cannot be null")
    @NotEmpty(message = "Permission name cannot be empty")
    @NotBlank(message = "Permission name cannot be blank")
    @Column(name = "permission_name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "Create permission cannot be null")
    @Column(name = "permission_create", nullable = false)
    private boolean create;

    @NotNull(message = "Read permission cannot be null")
    @Column(name = "permission_read", nullable = false)
    private boolean read;

    @NotNull(message = "Update permission cannot be null")
    @Column(name = "permission_update", nullable = false)
    private boolean update;

    @NotNull(message = "Delete permission cannot be null")
    @Column(name = "permission_delete", nullable = false)
    private boolean delete;

    @ManyToMany()
    @JoinTable(
            name = "role_crudpermissions",
            joinColumns = @JoinColumn(name = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
