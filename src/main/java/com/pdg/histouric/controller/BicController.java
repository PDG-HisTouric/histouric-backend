package com.pdg.histouric.controller;

import com.pdg.histouric.api.BicAPI;
import com.pdg.histouric.dto.CreateBicDTO;
import com.pdg.histouric.dto.ResponseBicDTO;
import com.pdg.histouric.dto.ResponseHistoryDTO;
import com.pdg.histouric.mapper.BicMapper;
import com.pdg.histouric.mapper.HistoryMapper;
import com.pdg.histouric.model.BIC;
import com.pdg.histouric.service.BicService;
import com.pdg.histouric.service.FirebaseStorageService;
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
    private final HistoryMapper historyMapper;
    private final FirebaseStorageService firebaseStorageService;

    @Override
    public ResponseBicDTO createBIC(@Valid CreateBicDTO createBicDTO) {
        BIC bic = bicMapper.fromDTO(createBicDTO);
        bic = bicService.createBIC(bic);
        List<ResponseHistoryDTO> histories = bic.getBicHistories().stream()
                .map(bicHistory -> firebaseStorageService.putUrlsToHistory(bicHistory.getHistory()))
                .map(historyMapper::fromHistoryToDTO)
                .toList();
        ResponseBicDTO responseBicDTO = bicMapper.fromBIC(bic);
        responseBicDTO.setHistories(histories);
        return responseBicDTO;
    }

    @Override
    public List<ResponseBicDTO> getBICs() {
        return bicService.getBICs().stream().map(bicMapper::fromBIC).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public ResponseBicDTO getBicById(UUID id) {
        return bicMapper.fromBIC(bicService.getBicById(id));
    }

    @Override
    public ResponseBicDTO updateBicById(UUID bicId, @Valid CreateBicDTO createBicDTO) {
        return bicMapper.fromBIC(bicService.updateBicById(bicId, bicMapper.fromDTO(createBicDTO)));
    }

    @Override
    public void deleteBicById(UUID bicId) {
        bicService.deleteBicById(bicId);
    }
}
