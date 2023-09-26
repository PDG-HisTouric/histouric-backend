package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.HistoryDTO;
import com.pdg.histouric.dto.HistoryImageDTO;
import com.pdg.histouric.dto.TextDTO;
import com.pdg.histouric.dto.VideoDTO;
import com.pdg.histouric.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    History fromDTOToHistory(HistoryDTO historyDTO);
    @Mapping(target = "owner", source = "owner.id")
    HistoryDTO fromHistoryToDTO(History history);
    Video fromDTOToVideo(VideoDTO videoDTO);
    @Mapping(target="historyId", source="history.id")
    VideoDTO fromVideoToDTO(Video video);
    Text fromDTOToText(TextDTO textDTO);
    @Mapping(target="historyId", source="history.id")
    TextDTO fromTextToDTO(Text text);
    HistoryImage fromDTOToHistoryImage(HistoryImageDTO historyImageDTO);
    @Mapping(target="historyId", source="history.id")
    HistoryImageDTO fromHistoryImageToDTO(HistoryImage historyImage);

    @Mapping(target = "id", source = "owner")
    HistouricUser fromUUIDToHistouricUser(UUID owner);

}
