package com.pdg.histouric.controller;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import com.pdg.histouric.api.RouteAPI;
import com.pdg.histouric.dto.CreateRouteDTO;
import com.pdg.histouric.dto.ResponseBicDTO;
import com.pdg.histouric.dto.ResponseRouteDTO;
import com.pdg.histouric.dto.ResponseSimpleRouteDTO;
import com.pdg.histouric.mapper.BicMapper;
import com.pdg.histouric.mapper.HistoryMapper;
import com.pdg.histouric.mapper.RouteMapper;
import com.pdg.histouric.model.History;
import com.pdg.histouric.model.Route;
import com.pdg.histouric.model.RouteBICHistory;
import com.pdg.histouric.service.FirebaseStorageService;
import com.pdg.histouric.service.RouteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class RouteController implements RouteAPI {

    private final RouteService routeService;
    private final FirebaseStorageService firebaseStorageService;
    private final GeoApiContext geoApiContext;

    private final HistoryMapper historyMapper;
    private final RouteMapper routeMapper;
    private final BicMapper bicMapper;

    @Override
    public ResponseRouteDTO createRoute(@Valid CreateRouteDTO createRouteDTO) {
        Route route = routeService.createRoute(routeMapper.fromDTOToRoute(createRouteDTO));
        ResponseRouteDTO responseRouteDTO = routeMapper.fromRouteToDTO(route);
        responseRouteDTO.setBics(getBicsAndHistoriesOfARoute(route));
        return responseRouteDTO;
    }

    @Override
    public ResponseRouteDTO getRouteById(UUID id) {
        Route route = routeService.findRouteById(id);
        ResponseRouteDTO responseRouteDTO = routeMapper.fromRouteToDTO(route);
        responseRouteDTO.setBics(getBicsAndHistoriesOfARoute(route));
        return responseRouteDTO;
    }

    @Override
    public List<ResponseSimpleRouteDTO> getSimplifiedRoutes() {
        return routeService.findAllRoutes().stream()
                .map(routeMapper::fromRouteToSimpleDTO)
                .toList();
    }

    @Override
    public DirectionsResult getDirection(String origin, String destination) throws IOException, InterruptedException, ApiException {
        String[] origins = origin.split(",");
        String[] destinations = destination.split(",");
        DirectionsResult directionsResult = DirectionsApi.newRequest(geoApiContext)
                .origin(new LatLng(Double.parseDouble(origins[0]), Double.parseDouble(origins[1])))
                .destination(new LatLng(Double.parseDouble(destinations[0]), Double.parseDouble(destinations[1])))
                .mode(TravelMode.WALKING)
                .await();
        return directionsResult;
    }

    @Override
    public DirectionsResult getDirectionWithWaypoints(String origin, String destination, String waypoints) {
        return null;
    }

    private List<ResponseBicDTO> getBicsAndHistoriesOfARoute(Route route) {
        List<RouteBICHistory> routeBICHistories = route.getRouteBICHistories();

        List<ResponseBicDTO> responseBicDTOS = routeBICHistories.stream()
                .map(RouteBICHistory::getBicHistory)
                .map(bicHistory -> bicMapper.fromBIC(bicHistory.getBic()))
                .toList();

        for (int i = 0; i < responseBicDTOS.size(); i++) {
            History history = routeBICHistories.get(i).getBicHistory().getHistory();
            history = firebaseStorageService.putUrlsToHistory(history);
            responseBicDTOS.get(i).setHistories(List.of(historyMapper.fromHistoryToDTO(history)));
        }

        return responseBicDTOS;
    }


}
