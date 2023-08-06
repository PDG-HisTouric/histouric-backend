package com.pdg.histouric.service.impl;

import com.pdg.histouric.constant.RoleErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
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

    private boolean theCurrentUserIsNotAnAdmin(){
        return !getCurrentUserRoles().contains("ADMIN");
    }

    private boolean isTheCurrentUserTheSameUser(UUID userId){
        return getCurrentUserId().equals(userId);
    }

    private void checkPermission(HistouricUser histouricUser){
        if(theCurrentUserIsNotAnAdmin() && !isTheCurrentUserTheSameUser(histouricUser.getId())){
            throw new UserException(
                    HttpStatus.FORBIDDEN,
                    new UserError(UserErrorCode.CODE_03, UserErrorCode.CODE_03.getMessage())
            );
        }
    }

    private HistouricUser getUserByUUID(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserException(
                HttpStatus.NOT_FOUND,
                new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
        ));
    }

    private void checkPermission(UUID userId){
        checkPermission(getUserByUUID(userId));
    }

    @Override
    public HistouricUser createUser(HistouricUser user) {
        if (theCurrentUserIsNotAnAdmin()) {
            user.setRoles(getTourismManagerRole());
        }
        return (HistouricUser) userRepository.findByUsername(user.getUsername())
                .map(userFound -> {
                    throw new UserException(
                            HttpStatus.BAD_REQUEST,
                            new UserError(UserErrorCode.CODE_02, UserErrorCode.CODE_02.getMessage())
                    );
                })
                .orElseGet(() -> userRepository.save(user));
    }

    private List<Role> getTourismManagerRole(){
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
    public HistouricUser getUserByUsername(String username) {
        HistouricUser histouricUser = userRepository.findByUsername(username).orElseThrow(() -> new UserException(
                HttpStatus.NOT_FOUND,
                new UserError(UserErrorCode.CODE_01, UserErrorCode.CODE_01.getMessage())
        ));
        checkPermission(histouricUser);
        return histouricUser;
    }

    @Override
    public HistouricUser updateUser(UUID userId, HistouricUser user) {
        HistouricUser histouricUser = getUserByUUID(userId);
        checkPermission(histouricUser);
        histouricUser.setUsername(user.getUsername());
        histouricUser.setEmail(user.getEmail());
        histouricUser.setPassword(user.getPassword());
        histouricUser.setRoles(user.getRoles());

        return userRepository.save(histouricUser);
    }

    @Override
    public void deleteUser(UUID userId) {
        checkPermission(userId);
        userRepository.deleteById(userId);
    }
}
