package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserErrorCode {

    CODE_01("User not found"),
    CODE_02("User already exists."),
    CODE_03("Not enough permissions."),

    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
