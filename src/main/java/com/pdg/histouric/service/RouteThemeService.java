package com.pdg.histouric.service;

import com.pdg.histouric.model.RouteTheme;

import java.util.List;
import java.util.UUID;

public interface RouteThemeService {
    List<RouteTheme> getAllRouteThemes();
    RouteTheme getRouteThemeById(UUID id);
}
