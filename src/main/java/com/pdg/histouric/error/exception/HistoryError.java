package com.pdg.histouric.error.exception;

import com.pdg.histouric.constant.BicErrorCode;
import com.pdg.histouric.constant.HistoryErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HistoryError {
    private HistoryErrorCode code;
    private String message;
}
