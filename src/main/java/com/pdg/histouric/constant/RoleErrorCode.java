package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleErrorCode {

    CODE_01("Role not found"),
    CODE_02("Role already exists."),

    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
