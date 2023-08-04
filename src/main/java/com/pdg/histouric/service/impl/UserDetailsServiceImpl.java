package com.pdg.histouric.service.impl;

import com.pdg.histouric.model.HistouricUser;
import com.pdg.histouric.model.Role;
import com.pdg.histouric.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HistouricUser userDetails = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<Role> roles = userRepository.getRolesByUserId(userDetails.getId()).orElseGet(ArrayList::new);
        userDetails.setRoles(roles);
        return userDetails;
    }
}
