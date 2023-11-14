package com.pdg.histouric.controller;

import com.pdg.histouric.api.HistoryAPI;
import com.pdg.histouric.dto.CreateHistoryDTO;
import com.pdg.histouric.dto.ResponseHistoryDetailDTO;
import com.pdg.histouric.mapper.HistoryMapper;
import com.pdg.histouric.model.History;
import com.pdg.histouric.service.FirebaseStorageService;
import com.pdg.histouric.service.HistoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class HistoryController implements HistoryAPI {

    private final HistoryService historyService;
    private final FirebaseStorageService firebaseStorageService;
    private final HistoryMapper historyMapper;

    @Override
    public ResponseHistoryDetailDTO createHistory(@Valid CreateHistoryDTO createHistoryDTO) throws IOException {
        return historyMapper.fromHistoryToDTO(historyService.createHistory(historyMapper.fromDTOToHistory(createHistoryDTO)));
    }

    @Override
    public void deleteHistory(UUID historyId) {
        historyService.deleteHistory(historyId);
    }

    @Override
    public ResponseHistoryDetailDTO updateHistory(CreateHistoryDTO createHistoryDTO) {
        return historyMapper.fromHistoryToDTO(historyService.updateHistory(historyMapper.fromDTOToHistory(createHistoryDTO)));
    }

    @Override
    public ResponseHistoryDetailDTO getHistoryById(UUID historyId) {
        History history = historyService.getHistoryById(historyId);
        return historyMapper.fromHistoryToDTO(firebaseStorageService.putUrlsToHistory(history));
    }

    @Override
    public List<ResponseHistoryDetailDTO> getHistoriesByTitle(@NotNull @NotEmpty String historyTitle) {
        return historyService.getHistoriesByTitle(historyTitle).stream()
                .map(firebaseStorageService::putUrlsToHistory)
                .map(historyMapper::fromHistoryToDTO).toList();
    }
}
