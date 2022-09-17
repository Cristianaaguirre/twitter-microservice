package com.auth.configuration.controller;

import com.auth.configuration.models.dto.LoginDTO;
import com.auth.configuration.models.dto.TokenDTO;
import com.auth.configuration.models.dto.UserDTO;
import com.auth.configuration.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
      return ResponseEntity.ok(service.register(dto));
   }

   @PostMapping("/login")
   public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto) {
      return ResponseEntity.ok(service.login(dto));
   }

   @PostMapping("/validate")
   public ResponseEntity<TokenDTO> validate(@RequestParam String token) {
      return ResponseEntity.ok(service.validate(token));
   }

   @GetMapping("/me")
   public ResponseEntity<String> getPersonalInformation(@RequestHeader String Authentication) {
      return null;
   }

}
