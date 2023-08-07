package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.RoleErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.dto.UpdateUserDTO;
import com.pdg.histouric.error.exception.RoleError;
import com.pdg.histouric.error.exception.RoleException;
import com.pdg.histouric.error.exception.UserError;
import com.pdg.histouric.error.exception.UserException;
import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Role;
import com.pdg.histouric.repository.RoleRepository;
import com.pdg.histouric.repository.UserRepository;
import com.pdg.histouric.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.pdg.histouric.security.HistouricSecurityContext.getCurrentUserId;
import static com.pdg.histouric.security.HistouricSecurityContext.getCurrentUserRoles;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public HistouricUser createUser(HistouricUser user) {
        if (!isAdmin()) {
            user.setRoles(getTourismManagerRole());
        }
        return (HistouricUser) userRepository.findByNickname(user.getNickname())
                .map(userFound -> {
                    throw new UserException(
                            HttpStatus.BAD_REQUEST,
                            new UserError(UserErrorCode.CODE_02, UserErrorCode.CODE_02.getMessage())
                    );
                })
                .orElseGet(() -> userRepository.save(user));
    }

    private boolean isAdmin() {
        return getCurrentUserRoles().contains("ADMIN");
    }

    private List<Role> getTourismManagerRole() {
        Role tourismManagerRole = roleRepository.findByName("TOURISM_MANAGER").orElseThrow(() -> new RoleException(
                HttpStatus.NOT_FOUND,
                new RoleError(RoleErrorCode.CODE_01, RoleErrorCode.CODE_01.getMessage())
        ));
        List<Role> roles = new ArrayList<>();
        roles.add(tourismManagerRole);
        return roles;
    }

    @Override
    public List<HistouricUser> getUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public HistouricUser getUserByNickname(String nickname) {
        HistouricUser histouricUser = userRepository.findByNickname(nickname).orElseThrow(() -> new UserException(
                HttpStatus.NOT_FOUND,
                new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
        ));
        checkPermission(histouricUser);
        return histouricUser;
    }

    private void checkPermission(HistouricUser histouricUser) {
        if (!isAdmin() && !verifyUserIdentity(histouricUser.getId())) {
            throw new UserException(
                    HttpStatus.FORBIDDEN,
                    new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage())
            );
        }
    }

    private boolean verifyUserIdentity(UUID userId) {
        UUID currentUserId = getCurrentUserId().orElseThrow(
                () -> new UserException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        new UserError(UserErrorCode.CODE_04, UserErrorCode.CODE_04.getMessage())
                )
        );
        return currentUserId.equals(userId);
    }

    @Override
    public HistouricUser updateUser(UUID userId, HistouricUser histouricUser) {

        if (!isAdmin() && histouricUser.getRoles() != null) {
            throw new UserException(
                    HttpStatus.FORBIDDEN,
                    new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage() + " You can't change roles")
            );
        }

        HistouricUser histouricUserInDB = getUserByUUID(userId);
        checkPermission(histouricUserInDB);
        if (histouricUser.getNickname() != null) histouricUserInDB.setNickname(histouricUser.getNickname());
        if (histouricUser.getEmail() != null) histouricUserInDB.setEmail(histouricUser.getEmail());
        if (histouricUser.getPassword() != null) histouricUserInDB.setPassword(passwordEncoder.encode(histouricUser.getPassword()));

        if (isAdmin() && histouricUser.getRoles() != null) histouricUserInDB.setRoles(histouricUser.getRoles());

        return userRepository.save(histouricUserInDB);
    }

    private HistouricUser getUserByUUID(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException(
                HttpStatus.NOT_FOUND,
                new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
        ));
    }

    @Override
    public void deleteUser(UUID userId) {
        checkPermission(getUserByUUID(userId));
        userRepository.deleteById(userId);
    }
}
