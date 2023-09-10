package com.pdg.histouric.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class BicException extends RuntimeException {
    private HttpStatus httpStatus;
    private BicError error;
}
