package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.repository.UserRepository;
import com.pdg.histouric.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public HistouricUser createUser(HistouricUser user) {
        return (HistouricUser) userRepository.findByUsername(user.getUsername())
                .map(userFound -> {
                    throw new UserException(
                            HttpStatus.BAD_REQUEST,
                            new UserError(UserErrorCode.CODE_02, UserErrorCode.CODE_02.getMessage())
                    );
                })
                .orElseGet(() -> userRepository.save(user));
    }

    @Override
    public List<HistouricUser> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public HistouricUser getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserException(
                HttpStatus.NOT_FOUND,
                new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
        ));
    }

    @Override
    public HistouricUser updateUser(UUID userId, HistouricUser user) {
        return userRepository.findById(userId)
                .map(userFound -> {
                    userFound.setUsername(user.getUsername());
                    userFound.setEmail(user.getEmail());
                    userFound.setPassword(user.getPassword());
                    userFound.setRoles(user.getRoles());
                    return userRepository.save(userFound);
                })
                .orElseThrow(() -> new UserException(
                        HttpStatus.NOT_FOUND,
                        new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
                ));
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
