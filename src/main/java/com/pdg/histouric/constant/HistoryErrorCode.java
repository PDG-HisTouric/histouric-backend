package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HistoryErrorCode {
    CODE_01("History not found"),
    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
