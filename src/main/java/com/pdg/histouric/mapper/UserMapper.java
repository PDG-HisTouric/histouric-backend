package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.UserDTO;
import com.pdg.histouric.model.HistouricUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    HistouricUser fromDTO(UserDTO userDTO);
    UserDTO fromUser(HistouricUser user);
}
