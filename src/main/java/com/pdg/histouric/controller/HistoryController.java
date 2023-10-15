package com.pdg.histouric.controller;

import com.pdg.histouric.api.HistoryAPI;
import com.pdg.histouric.dto.HistoryDTO;
import com.pdg.histouric.mapper.HistoryMapper;
import com.pdg.histouric.service.HistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class HistoryController implements HistoryAPI {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    @Override
    public HistoryDTO createHistory(@Valid HistoryDTO historyDTO) {
        return historyMapper.fromHistoryToDTO(historyService.createHistory(historyMapper.fromDTOToHistory(historyDTO)));
    }

    @Override
    public void deleteHistory(UUID historyId) {
        historyService.deleteHistory(historyId);
    }

    @Override
    public List<HistoryDTO> getHistoriesByImageUri(String imageUri) {
        return historyService.getHistoriesByImageUri(imageUri).stream().map(historyMapper::fromHistoryToDTO).toList();
    }

    @Override
    public List<HistoryDTO> getHistoriesByVideoUri(String videoUrl) {
        return historyService.getHistoriesByVideoUri(videoUrl).stream().map(historyMapper::fromHistoryToDTO).toList();
    }

    @Override
    public HistoryDTO updateHistory(HistoryDTO historyDTO) {
        return historyMapper.fromHistoryToDTO(historyService.updateHistory(historyMapper.fromDTOToHistory(historyDTO)));
    }
}
