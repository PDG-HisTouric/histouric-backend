package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.BICImageDTO;
import com.pdg.histouric.dto.BicDTO;
import com.pdg.histouric.dto.NicknameDTO;
import com.pdg.histouric.model.BIC;
import com.pdg.histouric.model.BICImage;
import com.pdg.histouric.model.Nickname;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BicMapper {
    @Mapping(target = "images", source = "imagesUris")
    BIC fromDTO(BicDTO bicDTO);

    @Mapping(target = "imagesUris", source = "images")
    BicDTO fromBIC(BIC bic);

    @Mapping(target = "nickname", source = "nickname")
    Nickname fromNicknameDTO(NicknameDTO nicknameDTO);

    @Mapping(target = "nickname", source = "nickname")
    NicknameDTO fromNickname(Nickname nickname);

    @Mapping(target = "imageUri", source = "imageUri")
    BICImage fromBICImageDTO(BICImageDTO imageDTO);

    @Mapping(target = "imageUri", source = "imageUri")
    BICImageDTO fromBICImage(BICImage image);
}
