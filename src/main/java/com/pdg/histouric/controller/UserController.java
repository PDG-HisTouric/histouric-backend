package com.pdg.histouric.controller;

import com.pdg.histouric.api.UserAPI;
import com.pdg.histouric.dto.UserDTO;
import com.pdg.histouric.mapper.UserMapper;
import com.pdg.histouric.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public UserDTO createUser(@Valid UserDTO userDTO) {
        return userMapper.fromUser(userService.createUser(userMapper.fromDTO(userDTO)));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::fromUser).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.fromUser(userService.getUserByUsername(username));
    }

    @Override
    public UserDTO updateUser(UUID userId, @Valid UserDTO userDTO) {
        return userMapper.fromUser(userService.updateUser(userId, userMapper.fromDTO(userDTO)));
    }

    @Override
    public void deleteUser(UUID userId) {
        userService.deleteUser(userId);
    }
}
