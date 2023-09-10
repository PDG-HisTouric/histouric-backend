package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.BicDTO;
import com.pdg.histouric.model.BIC;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BicMapper {
    BIC fromDTO(BicDTO bicDTO);
    BicDTO fromBIC(BIC bic);
}
