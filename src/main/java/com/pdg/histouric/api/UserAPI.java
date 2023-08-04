package com.pdg.histouric.api;

import com.pdg.histouric.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users")
public interface UserAPI {
    String ROOT_PATH = "/api/v1/users";

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @GetMapping
    List<UserDTO> getUsers();

    @GetMapping("/{username}")
    UserDTO getUserByUsername(@PathVariable String username);

    @PutMapping("/{userId}")
    UserDTO updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable UUID userId);
}
