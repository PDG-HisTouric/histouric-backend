package com.pdg.histouric.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class PermissionException extends RuntimeException{
    private HttpStatus httpStatus;
    private PermissionError error;
}
