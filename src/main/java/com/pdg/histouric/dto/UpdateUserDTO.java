package com.pdg.histouric.dto;

import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;

import java.util.*;
import java.util.stream.Collectors;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {

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

    @NotNull(message = "User password cannot be null")
    @NotEmpty(message = "User password cannot be empty")
    @NotBlank(message = "User password cannot be blank")
    @Length(max = 255, message = "User password must be 255 characters long")
    private String password;

    private List<RoleDTO> roles;

    public void validate() {
        Validator validator = buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(this);

        Set<String> propertyPathsToDelete = violations.stream()
                .filter(violation ->
                        violation.getConstraintDescriptor()
                                .getAnnotation()
                                .annotationType()
                                .getName().equals("jakarta.validation.constraints.NotNull"))
                .map(violation -> violation.getPropertyPath().toString())
                .collect(Collectors.toSet());

        Set<ConstraintViolation<UpdateUserDTO>> filteredViolations = violations.stream()
                .filter(violation -> !propertyPathsToDelete.contains(violation.getPropertyPath().toString()))
                .collect(Collectors.toSet());

        if (!filteredViolations.isEmpty()) {
            String errorsMessages = filteredViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", "));
            System.out.println(errorsMessages);
            throw new UserException(
                    HttpStatus.BAD_REQUEST,
                    new UserError(UserErrorCode.UNIVERSAL_ANNOTATION_CODE,
                            Objects.requireNonNull(errorsMessages)
            ));
        }
    }
}
