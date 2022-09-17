package com.auth.configuration.service;

import com.auth.configuration.config.JwtProvider;
import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.dto.TokenDTO;
import com.auth.configuration.models.dto.UserDTO;
import com.auth.configuration.models.entity.UserEntity;
import com.auth.configuration.models.mapper.TypeStatus;
import com.auth.configuration.models.mapper.UserMapper;
import com.auth.configuration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.Consumer;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

   private final UserRepository repository;
   private final JwtProvider jwtProvider;
   private final PasswordEncoder encoder;

   public UserDTO register(LoginDTO dto) {
      var check = repository.existsByUsername(dto.getUsername());

      if(check)
         throw new ResponseStatusException(
            CONFLICT,
            String.format("User %s already exists", dto.getUsername())
         );


      var entity = repository.save(
         new UserEntity(
            dto.getUsername(),
            encoder.encode(dto.getPassword()),
            TypeStatus.NORMAL
            )
      );

      return UserMapper.entityToDTO(entity);
   }

   public TokenDTO login(LoginDTO dto) {
      var user = repository.findByUsername(dto.getUsername())
         .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED));

      if(encoder.matches(dto.getPassword(), user.getPassword())){
         var token = jwtProvider.createToken(user);
         return new TokenDTO(token);
      }
      else
         throw new ResponseStatusException(UNAUTHORIZED);
   }

   public TokenDTO validate(String token) {
     if(!jwtProvider.isTokenExpired(token)){

         var username = jwtProvider.extractUsername(token);
         repository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED));
         jwtProvider.validateSignature(token);

         return new TokenDTO(token);
      } else
         throw new ResponseStatusException(UNAUTHORIZED);
   }
}
