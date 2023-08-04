package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermissionErrorCode {
    CODE_01("Permission not found"),
    CODE_02("Permission already exists."),

    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
