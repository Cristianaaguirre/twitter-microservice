package com.auth.configuration.service;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.entity.UserEntity;

public interface AuthService {
   UserEntity saveUser(LoginDTO dto);
}
