package com.pdg.histouric.controller;

import com.pdg.histouric.api.HistoryAPI;
import com.pdg.histouric.dto.CreateHistoryDTO;
import com.pdg.histouric.dto.ResponseHistoryDTO;
import com.pdg.histouric.mapper.HistoryMapper;
import com.pdg.histouric.service.HistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class HistoryController implements HistoryAPI {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    @Override
    public ResponseHistoryDTO createHistory(@Valid CreateHistoryDTO createHistoryDTO) throws IOException {
        return historyMapper.fromHistoryToDTO(historyService.createHistory(historyMapper.fromDTOToHistory(createHistoryDTO)));
    }

    @Override
    public void deleteHistory(UUID historyId) {
        historyService.deleteHistory(historyId);
    }

    @Override
    public List<ResponseHistoryDTO> getHistoriesByImageUri(String imageUri) {
        return historyService.getHistoriesByImageUri(imageUri).stream().map(historyMapper::fromHistoryToDTO).toList();
    }

    @Override
    public List<ResponseHistoryDTO> getHistoriesByVideoUri(String videoUrl) {
        return historyService.getHistoriesByVideoUri(videoUrl).stream().map(historyMapper::fromHistoryToDTO).toList();
    }

    @Override
    public ResponseHistoryDTO updateHistory(CreateHistoryDTO createHistoryDTO) {
        return historyMapper.fromHistoryToDTO(historyService.updateHistory(historyMapper.fromDTOToHistory(createHistoryDTO)));
    }

    @Override
    public ResponseHistoryDTO getHistoryById(UUID historyId) {
        return historyMapper.fromHistoryToDTO(historyService.getHistoryById(historyId));
    }
}
