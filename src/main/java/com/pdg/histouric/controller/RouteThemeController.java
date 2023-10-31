package com.pdg.histouric.controller;

import com.pdg.histouric.api.RouteThemeAPI;
import com.pdg.histouric.dto.ResponseRouteThemeDTO;
import com.pdg.histouric.mapper.RouteThemeMapper;
import com.pdg.histouric.service.RouteThemeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class RouteThemeController implements RouteThemeAPI {

    private final RouteThemeService routeThemeService;
    private final RouteThemeMapper routeThemeMapper;

    @Override
    public List<ResponseRouteThemeDTO> getRouteThemes() {
        return routeThemeService.getAllRouteThemes()
                .stream()
                .map(routeThemeMapper::fromRouteTheme)
                .toList();
    }

    @Override
    public ResponseRouteThemeDTO getRouteThemeById(UUID id) {
        return routeThemeMapper.fromRouteTheme(routeThemeService.getRouteThemeById(id));
    }
}
