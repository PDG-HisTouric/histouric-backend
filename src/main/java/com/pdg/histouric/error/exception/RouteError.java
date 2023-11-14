package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.RouteErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteError {
    private RouteErrorCode code;
    private String message;
}
