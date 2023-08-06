package com.pdg.histouric.service;

import com.pdg.histouric.dto.UpdateUserDTO;
import com.pdg.histouric.model.HistouricUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface UserService {
    HistouricUser createUser(HistouricUser user);
    List<HistouricUser> getUsers();
    HistouricUser getUserByUsername(String username);
    HistouricUser updateUser(UUID userId, HistouricUser user);
    void deleteUser(UUID userId);
}
