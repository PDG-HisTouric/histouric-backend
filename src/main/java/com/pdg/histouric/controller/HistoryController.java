package com.pdg.histouric.controller;

import com.pdg.histouric.api.HistoryAPI;
import com.pdg.histouric.dto.HistoryDTO;
import com.pdg.histouric.mapper.HistoryMapper;
import com.pdg.histouric.service.HistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HistoryController implements HistoryAPI {

    private final HistoryService historyService;
    private final HistoryMapper historyMapper;

    @Override
    public HistoryDTO createHistory(@Valid HistoryDTO historyDTO) {
        return historyMapper.fromHistoryToDTO(historyService.createHistory(historyMapper.fromDTOToHistory(historyDTO)));
    }
}
