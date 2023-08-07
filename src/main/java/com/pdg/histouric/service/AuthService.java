package com.pdg.histouric.service;

import com.pdg.histouric.dto.TokenDTO;
import com.pdg.histouric.dto.UserForLoginDTO;

public interface AuthService {
    TokenDTO login(UserForLoginDTO userForLoginDTO);

}
