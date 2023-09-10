package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.BicErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BicError {
    private BicErrorCode code;
    private String message;
}
