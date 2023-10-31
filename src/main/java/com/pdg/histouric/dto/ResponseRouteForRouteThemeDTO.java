package com.pdg.histouric.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRouteForRouteThemeDTO {

    private UUID id;
    private String name;
    private String description;

}
