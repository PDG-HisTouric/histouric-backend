package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.CreateUserDTO;
import com.pdg.histouric.dto.ResponseUserDTO;
import com.pdg.histouric.dto.UpdateUserDTO;
import com.pdg.histouric.model.HistouricUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    HistouricUser fromDTO(CreateUserDTO createUserDTO);
    CreateUserDTO fromUserToCreateDTO(HistouricUser user);
    ResponseUserDTO fromUserToResponseDTO(HistouricUser user);

    HistouricUser fromUpdateDTO(UpdateUserDTO updateUserDTO);

}
