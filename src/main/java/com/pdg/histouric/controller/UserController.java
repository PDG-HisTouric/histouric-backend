package com.pdg.histouric.controller;

import com.pdg.histouric.api.UserAPI;
import com.pdg.histouric.dto.CreateUserDTO;
import com.pdg.histouric.dto.ResponseUserDTO;
import com.pdg.histouric.dto.UpdateUserDTO;
import com.pdg.histouric.dto.validation_groups.EmailNotNullGroup;
import com.pdg.histouric.dto.validation_groups.UsernameNotNullGroup;
import com.pdg.histouric.mapper.UserMapper;
import com.pdg.histouric.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseUserDTO createUser(@Valid CreateUserDTO createUserDTO) {
        return userMapper.fromUserToResponseDTO(userService.createUser(userMapper.fromDTO(createUserDTO)));
    }

    @Override
    public List<ResponseUserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUserToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO getUserByNickname(String nickname) {
        return userMapper.fromUserToResponseDTO(userService.getUserByNickname(nickname));
    }

    @Override
    public ResponseUserDTO updateUser(UUID userId, UpdateUserDTO updateUserDTO) {
        updateUserDTO.validate();
        return userMapper.fromUserToResponseDTO(userService.updateUser(userId, userMapper.fromUpdateDTO(updateUserDTO)));
    }

    @Override
    public void deleteUser(UUID userId) {
        userService.deleteUser(userId);
    }
}
