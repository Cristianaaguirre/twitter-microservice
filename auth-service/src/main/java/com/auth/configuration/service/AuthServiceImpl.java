package com.auth.configuration.service;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.entity.UserEntity;
import com.auth.configuration.models.mapper.UserMapper;
import com.auth.configuration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

   private final UserRepository repository;

   public UserEntity saveUser(LoginDTO dto) {
      var check = repository.existsByUsername(dto.getUsername());

      if(check) throw new RuntimeException("username is already exists");

      return repository.save(
         UserMapper.dtoToEntity(dto)
      );
   }
}
