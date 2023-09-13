package com.pdg.histouric.api;

import com.pdg.histouric.dto.BicDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/bics")
public interface BicAPI {

    String ROOT_PATH = "/api/v1/bics";

    @PostMapping
    BicDTO createBIC(@RequestBody BicDTO bicDTO);

    @GetMapping
    List<BicDTO> getBICs();

    @GetMapping("/{id}")
    BicDTO getBicById(@PathVariable UUID id);

    @GetMapping("/name/{nameOrNickname}")
    List<BicDTO> getBicByNameOrNickname(@PathVariable String nameOrNickname);

    @PutMapping("/{bicId}")
    BicDTO updateBicById(@PathVariable UUID bicId, @RequestBody BicDTO bicDTO);

    @DeleteMapping("/{bicId}")
    void deleteBicById(@PathVariable UUID bicId);
}
