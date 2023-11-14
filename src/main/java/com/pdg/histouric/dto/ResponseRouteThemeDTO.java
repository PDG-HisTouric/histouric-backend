package com.pdg.histouric.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRouteThemeDTO {

    private UUID id;
    private String name;
    private List<ResponseRouteForRouteThemeDTO> routes;

}