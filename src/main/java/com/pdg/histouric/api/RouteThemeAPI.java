package com.pdg.histouric.api;

import com.pdg.histouric.dto.ResponseRouteThemeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/route-themes")
public interface RouteThemeAPI {
    String ROOT_PATH = "/api/v1/route-themes";

    @GetMapping
    List<ResponseRouteThemeDTO> getRouteThemes();

    @GetMapping("/{id}")
    ResponseRouteThemeDTO getRouteThemeById(@PathVariable UUID id);

}
