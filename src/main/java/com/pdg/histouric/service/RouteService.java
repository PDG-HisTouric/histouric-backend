package com.pdg.histouric.service;

import com.pdg.histouric.model.Route;

import java.util.UUID;

public interface RouteService {
    Route createRoute(Route route);
    Route findRouteById(UUID id);
}
