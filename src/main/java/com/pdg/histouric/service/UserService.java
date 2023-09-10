package com.pdg.histouric.service;

import com.pdg.histouric.model.HistouricUser;
import java.util.List;
import java.util.UUID;

public interface UserService {
    HistouricUser createUser(HistouricUser user);
    List<HistouricUser> getUsers();
    HistouricUser getUserByNickname(String nickname);

    List<HistouricUser> getUsersByNickname(String nickname);
    HistouricUser updateUser(UUID userId, HistouricUser user);
    void deleteUser(UUID userId);
}
