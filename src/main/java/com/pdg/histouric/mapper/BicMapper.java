package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.*;
import com.pdg.histouric.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BicMapper {
    @Mapping(target = "images", source = "imagesUris")
    @Mapping(target = "bicHistories", source = "historiesIds")
    BIC fromDTO(CreateBicDTO createBicDTO);

    @Mapping(target = "id.historyId", source = "historyId")
    BICHistory fromString(String historyId);

    @Mapping(target = "imagesUris", source = "images")
    ResponseBicDTO fromBIC(BIC bic);

    @Mapping(target = "nickname", source = "nickname")
    Nickname fromNicknameDTO(NicknameDTO nicknameDTO);

    @Mapping(target = "nickname", source = "nickname")
    NicknameDTO fromNickname(Nickname nickname);

    @Mapping(target = "imageUri", source = "imageUri")
    BICImage fromBICImageDTO(BICImageDTO imageDTO);

    @Mapping(target = "imageUri", source = "imageUri")
    BICImageDTO fromBICImage(BICImage image);
}
