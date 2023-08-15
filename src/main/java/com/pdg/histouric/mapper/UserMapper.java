package com.pdg.histouric.mapper;

import com.pdg.histouric.dto.CreateUserDTO;
import com.pdg.histouric.dto.ResponseUserDTO;
import com.pdg.histouric.dto.UpdateUserDTO;
import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    HistouricUser fromDTO(CreateUserDTO createUserDTO);
    ResponseUserDTO fromUserToResponseDTO(HistouricUser user);
    HistouricUser fromUpdateDTO(UpdateUserDTO updateUserDTO);
    List<ResponseUserDTO> fromUsersToResponseDTOs(List<HistouricUser> users);
    List<Role> mapRoles(List<String> value);
    @Mapping(target = "name", source = "value")
    Role mapRole(String value);
}
