package com.pdg.histouric.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RouteErrorCode {
    CODE_01("No se encontró la ruta"),
    CODE_02("La historia y el BIC no están asociados"),

    UNIVERSAL_ANNOTATION_CODE("Method argument not valid.");

    private final String message;
}
