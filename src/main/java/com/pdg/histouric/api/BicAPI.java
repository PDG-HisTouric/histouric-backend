package com.pdg.histouric.api;

import com.pdg.histouric.dto.CreateBicDTO;
import com.pdg.histouric.dto.ResponseBicDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/bics")
public interface BicAPI {

    String ROOT_PATH = "/api/v1/bics";

    @PostMapping
    ResponseBicDTO createBIC(@RequestBody CreateBicDTO createBicDTO);

    @GetMapping
    List<ResponseBicDTO> getBICs();

    @GetMapping("/{id}")
    ResponseBicDTO getBicById(@PathVariable UUID id);

    @PutMapping("/{bicId}")
    ResponseBicDTO updateBicById(@PathVariable UUID bicId, @RequestBody CreateBicDTO createBicDTO);

    @DeleteMapping("/{bicId}")
    void deleteBicById(@PathVariable UUID bicId);
}
