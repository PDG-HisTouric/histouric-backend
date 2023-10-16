package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.*;
import com.pdg.histouric.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    History fromDTOToHistory(CreateHistoryDTO createHistoryDTO);
    @Mapping(target = "owner", source = "owner.id")
    ResponseHistoryDTO fromHistoryToDTO(History history);
    @Mapping(target = "historyId", source = "history.id")
    ResponseAudioDTO fromAudioToDTO(Audio audio);
    Video fromDTOToVideo(CreateVideoDTO createVideoDTO);
    @Mapping(target="historyId", source="history.id")
    ResponseVideoDTO fromVideoToDTO(Video video);
    Text fromDTOToText(TextDTO textDTO);
    @Mapping(target="historyId", source="history.id")
    TextDTO fromTextToDTO(Text text);
    HistoryImage fromDTOToHistoryImage(CreateHistoryImageDTO createHistoryImageDTO);
    @Mapping(target="historyId", source="history.id")
    ResponseHistoryImageDTO fromHistoryImageToDTO(HistoryImage historyImage);
    @Mapping(target = "id", source = "owner")
    HistouricUser fromUUIDToHistouricUser(UUID owner);

}
