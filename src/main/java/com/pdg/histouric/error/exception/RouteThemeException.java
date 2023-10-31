package com.pdg.histouric.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class RouteThemeException extends RuntimeException {
    private HttpStatus httpStatus;
    private RouteThemeError error;
}
