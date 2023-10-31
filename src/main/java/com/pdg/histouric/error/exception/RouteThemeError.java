package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.RouteThemeErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteThemeError {
    private RouteThemeErrorCode code;
    private String message;
}
