package com.pdg.histouric.api;

import com.pdg.histouric.dto.HistoryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/histories")
public interface HistoryAPI {
    String ROOT_PATH = "/api/v1/histories";

    @PostMapping
    HistoryDTO createHistory(@RequestBody HistoryDTO historyDTO);

    @DeleteMapping("/{historyId}")
    void deleteHistory(@PathVariable UUID historyId);

    @GetMapping("/image/{imageUri}")
    List<HistoryDTO> getHistoriesByImageUri(@PathVariable String imageUri);

    @GetMapping("/video/{videoUrl}")
    List<HistoryDTO> getHistoriesByVideoUri(@PathVariable String videoUrl);

    @PutMapping
    HistoryDTO updateHistory(@RequestBody HistoryDTO historyDTO);
}
