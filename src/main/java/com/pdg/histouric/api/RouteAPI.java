package com.pdg.histouric.api;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.pdg.histouric.dto.CreateRouteDTO;
import com.pdg.histouric.dto.ResponseRouteDTO;
import com.pdg.histouric.dto.ResponseSimpleRouteDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/routes")
public interface RouteAPI {
    String ROOT_PATH = "/api/v1/routes";

    @PostMapping
    ResponseRouteDTO createRoute(@RequestBody CreateRouteDTO createRouteDTO);

    @GetMapping("/{id}")
    ResponseRouteDTO getRouteById(@PathVariable UUID id);

    @GetMapping("/search")
    //Get the routes without the BICs
    List<ResponseSimpleRouteDTO> getSimplifiedRoutes(); //TODO: add pagination

    @GetMapping("/directions/origin/{originCoordinates}/destination/{destinationCoordinates}")
    DirectionsResult getDirection(@PathVariable("originCoordinates") String originCoordinates,
                                  @PathVariable("destinationCoordinates") String destinationCoordinates) throws IOException, InterruptedException, ApiException;

    @GetMapping("/directions/origin/{originCoordinates}/destination/{destinationCoordinates}/waypoints/{waypointsCoordinates}")
    DirectionsResult getDirectionWithWaypoints(@PathVariable("originCoordinates") String originCoordinates,
                                               @PathVariable("destinationCoordinates") String destinationCoordinates,
                                               @PathVariable("waypointsCoordinates") String waypointsCoordinates);
}
