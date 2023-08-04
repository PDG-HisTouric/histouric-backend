package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.PermissionErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionError {
    private PermissionErrorCode code;
    private String message;
}
