package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.RouteThemeErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.RouteThemeError;
import com.pdg.histouric.error.exception.RouteThemeException;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import com.pdg.histouric.model.*;
import com.pdg.histouric.repository.*;
import com.pdg.histouric.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserRepository histouricUserRepository;
    private final BICHistoryRepository bicHistoryRepository;
    private final RouteBICHistoryRepository routeBICHistoryRepository;

    @Override
    public Route createRoute(Route route) {
        List<RouteBICHistory> routeBICHistories = route.getRouteBICHistories();
        route.setRouteBICHistories(null);
        route.setOwner(findUserById(route.getOwner().getId()));
        Route createdRoute = routeRepository.save(route);
        int order = 0;
        for (RouteBICHistory routeBICHistory : routeBICHistories) {
            order++;
            BICHistory bicHistory = findBicHistoryById(routeBICHistory.getId().getBicHistoryPK());
            routeBICHistory.getId().setRouteId(createdRoute.getId());
            routeBICHistory.setBicHistory(bicHistory);
            routeBICHistory.setRoute(createdRoute);
            routeBICHistory.setBicOrder(order);
            routeBICHistoryRepository.save(routeBICHistory);
        }
        createdRoute.setRouteBICHistories(routeBICHistoryRepository.findAllById_RouteId_OrderByBicOrderAsc(createdRoute.getId()));
        return createdRoute;
    }

    @Override
    public Route findRouteById(UUID id) {
        return routeRepository.findById(id).orElseThrow(); //TODO: Create exception for route
    }

    private BICHistory findBicHistoryById(BICHistoryPK id) {
        return bicHistoryRepository.findById(id).orElseThrow(
                () -> new RouteThemeException(HttpStatus.BAD_REQUEST, new RouteThemeError(RouteThemeErrorCode.CODE_02, RouteThemeErrorCode.CODE_02.getMessage()))
        );
    }

    private HistouricUser findUserById(UUID id) {
        return histouricUserRepository.findById(id).orElseThrow(
                () -> new UserException(HttpStatus.NOT_FOUND, new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage()))
        );
    }

}
