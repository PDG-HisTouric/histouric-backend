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
public class Role {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Role id cannot be null")
    @Column(name = "role_id", nullable = false, unique = true)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotNull(message = "Role name cannot be null")
    @NotEmpty(message = "Role name cannot be empty")
    @NotBlank(message = "Role name cannot be blank")
    @Column(name = "role_name", nullable = false, unique = true)
    private String name;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotEmpty(message = "Role description cannot be empty")
    @NotBlank(message = "Role description cannot be blank")
    @Column(name = "role_description", nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "histouric_user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "histouric_user_id")
    )
    private List<HistouricUser> users;
}
