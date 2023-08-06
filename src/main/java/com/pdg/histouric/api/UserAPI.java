package com.pdg.histouric.api;

import com.pdg.histouric.dto.CreateUserDTO;
import com.pdg.histouric.dto.ResponseUserDTO;
import com.pdg.histouric.dto.UpdateUserDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
public interface UserAPI {
    String ROOT_PATH = "/api/v1/users";

    @PostMapping
    ResponseUserDTO createUser(@RequestBody CreateUserDTO createUserDTO);

    @GetMapping
    List<ResponseUserDTO> getUsers();

    @GetMapping("/{username}")
    ResponseUserDTO getUserByUsername(@PathVariable String username);

    @PutMapping("/{userId}")
    ResponseUserDTO updateUser(@PathVariable UUID userId, @RequestBody UpdateUserDTO updateUserDTO);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable UUID userId);
}
