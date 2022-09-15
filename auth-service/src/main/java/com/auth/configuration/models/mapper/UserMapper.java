package com.auth.configuration.models.mapper;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.entity.UserEntity;

public class UserMapper {

   public static UserEntity dtoToEntity(LoginDTO dto) {
      return UserEntity.builder()
         .username(dto.getUsername())
         .password(dto.getPassword())
         .status(TypeStatus.NORMAL)
         .build();
   }
}
