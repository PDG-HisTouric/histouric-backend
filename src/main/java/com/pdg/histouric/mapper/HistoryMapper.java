package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.HistoryDTO;
import com.pdg.histouric.model.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    History fromDTOToHistory(HistoryDTO historyDTO);
    HistoryDTO fromHistoryToDTO(History history);
}
