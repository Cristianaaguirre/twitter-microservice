package com.auth.configuration.service;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.dto.TokenDTO;
import com.auth.configuration.models.dto.UserDTO;

public interface AuthService {
   UserDTO register(LoginDTO dto);
   TokenDTO login(LoginDTO dto);
   TokenDTO validate(String token);
}
