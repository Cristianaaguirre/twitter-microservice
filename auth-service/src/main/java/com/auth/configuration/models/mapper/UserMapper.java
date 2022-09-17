package com.auth.configuration.models.mapper;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.dto.UserDTO;
import com.auth.configuration.models.entity.UserEntity;
import org.apache.tomcat.jni.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {


   public static UserDTO entityToDTO(UserEntity entity) {
      return UserDTO.builder()
         .id(entity.getId())
         .username(entity.getUsername())
         .userType(entity.getStatus().getName())
         .build();
   }
}
