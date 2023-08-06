package com.pdg.histouric.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class JPAError {
    private HttpStatus code;
    private String message;
}
