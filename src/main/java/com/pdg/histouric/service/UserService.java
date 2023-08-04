package com.pdg.histouric.service;

import com.pdg.histouric.model.HistouricUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface UserService {
    HistouricUser createUser(@RequestBody HistouricUser user);
    List<HistouricUser> getUsers();
    HistouricUser getUserByUsername(@PathVariable String username);
    HistouricUser updateUser(@PathVariable UUID userId, @RequestBody HistouricUser user);
    void deleteUser(@PathVariable UUID userId);
}
