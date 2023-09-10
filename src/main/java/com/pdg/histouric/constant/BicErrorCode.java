package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BicErrorCode {
    CODE_01("BIC not found"),
    CODE_02("BIC already exists"),

    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
