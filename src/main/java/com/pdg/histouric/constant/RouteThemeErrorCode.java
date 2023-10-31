package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RouteThemeErrorCode {
    CODE_01("Route theme not found"),
    CODE_02("History and bic are not associated"),

    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
