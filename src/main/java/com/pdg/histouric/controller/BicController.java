package com.pdg.histouric.controller;

import com.pdg.histouric.api.BicAPI;
import com.pdg.histouric.dto.BicDTO;
import com.pdg.histouric.mapper.BicMapper;
import com.pdg.histouric.service.BicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class BicController implements BicAPI {

    private final BicService bicService;
    private final BicMapper bicMapper;

    @Override
    public BicDTO createBIC(@Valid BicDTO bicDTO) {
        return bicMapper.fromBIC(bicService.createBIC(bicMapper.fromDTO(bicDTO)));
    }

    @Override
    public List<BicDTO> getBICs() {
        return bicService.getBICs().stream().map(bicMapper::fromBIC).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public BicDTO getBicById(UUID id) {
        return bicMapper.fromBIC(bicService.getBicById(id));
    }

    @Override
    public BicDTO updateBicById(UUID bicId, @Valid BicDTO bicDTO) {
        return bicMapper.fromBIC(bicService.updateBicById(bicId, bicMapper.fromDTO(bicDTO)));
    }

    @Override
    public void deleteBicById(UUID bicId) {
        bicService.deleteBicById(bicId);
    }
}
