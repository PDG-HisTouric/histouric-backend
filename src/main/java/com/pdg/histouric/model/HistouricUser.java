package com.pdg.histouric.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class HistouricUser {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @NotNull(message = "User id cannot be null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private UUID id;

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @NotBlank(message = "Username cannot be blank")
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @NotNull(message = "User email cannot be null")
    @NotEmpty(message = "User email cannot be empty")
    @NotBlank(message = "User email cannot be blank")
    @Email(regexp = "\\w+\\.?\\w+@\\w+\\.\\w+", message = "User email must be a valid email address")
    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "User password cannot be null")
    @NotEmpty(message = "User password cannot be empty")
    @NotBlank(message = "User password cannot be blank")
    @Column(name = "user_password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "histouric_user_role",
            joinColumns = @JoinColumn(name = "histouric_user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
