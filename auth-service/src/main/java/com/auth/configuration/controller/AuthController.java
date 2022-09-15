package com.auth.configuration.controller;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

   private final AuthService service;

   public AuthController(AuthService service) {
      this.service = service;
   }

   @PostMapping("/register")
   public ResponseEntity<?> register(@RequestBody LoginDTO dto) {
      return Optional.of(service.saveUser(dto))
         .map(ResponseEntity::ok)
         .orElse(ResponseEntity.status(500).build());
   }

   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
      return null;
   }

}
