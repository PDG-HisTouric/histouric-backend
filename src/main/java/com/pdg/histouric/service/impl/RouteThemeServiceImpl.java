package com.pdg.histouric.service.impl;

import com.pdg.histouric.model.RouteTheme;
import com.pdg.histouric.repository.RouteThemeRepository;
import com.pdg.histouric.service.RouteThemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RouteThemeServiceImpl implements RouteThemeService {

    private final RouteThemeRepository routeThemeRepository;

    @Override
    public List<RouteTheme> getAllRouteThemes() {
        return routeThemeRepository.findAll();
    }

    @Override
    public RouteTheme getRouteThemeById(UUID id) {
        return routeThemeRepository.findById(id).orElseThrow(); //TODO: Create exception for route theme
    }
}
