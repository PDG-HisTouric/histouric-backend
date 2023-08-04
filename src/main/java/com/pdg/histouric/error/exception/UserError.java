package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserError {
    private UserErrorCode code;
    private String message;
}
