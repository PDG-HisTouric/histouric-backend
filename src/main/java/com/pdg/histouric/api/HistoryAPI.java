package com.pdg.histouric.api;

import com.pdg.histouric.dto.CreateHistoryDTO;
import com.pdg.histouric.dto.ResponseHistoryDetailDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/histories")
public interface HistoryAPI {
    String ROOT_PATH = "/api/v1/histories";

    @PostMapping
    ResponseHistoryDetailDTO createHistory(@RequestBody CreateHistoryDTO createHistoryDTO) throws IOException;

    @DeleteMapping("/{historyId}")
    void deleteHistory(@PathVariable UUID historyId);

    @PutMapping
    ResponseHistoryDetailDTO updateHistory(@RequestBody CreateHistoryDTO createHistoryDTO);

    @GetMapping("/{historyId}")
    ResponseHistoryDetailDTO getHistoryById(@PathVariable UUID historyId);
    
    @GetMapping("/title/{historyTitle}") //TODO: add pagination
    List<ResponseHistoryDetailDTO> getHistoriesByTitle(@PathVariable String historyTitle);

    @GetMapping
    List<ResponseHistoryDetailDTO> getAllHistories(); //TODO: add pagination
}
