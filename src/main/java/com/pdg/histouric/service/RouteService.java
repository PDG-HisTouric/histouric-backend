package com.pdg.histouric.service;

import com.pdg.histouric.model.Route;

import java.util.List;
import java.util.UUID;

public interface RouteService {
    Route createRoute(Route route);
    Route findRouteById(UUID id);
    List<Route> findAllRoutes();
}
