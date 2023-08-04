package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.RoleErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleError {
    private RoleErrorCode code;
    private String message;
}
