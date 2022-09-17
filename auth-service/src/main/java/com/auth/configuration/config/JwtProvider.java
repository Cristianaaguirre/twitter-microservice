package com.auth.configuration.config;

import com.auth.configuration.models.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtProvider {

   @Value("${jwt.secret}")
   private String secret;

   private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      var claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
      return Jwts.parserBuilder()
         .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
         .build()
         .parseClaimsJws(token)
         .getBody();
   }

   private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   public boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   public String extractType(String token) {
      return extractClaim(token, c ->
         c.containsKey("type")
            ? c.get("type", String.class)
            : null
      );
   }

   public String createToken(UserEntity entity) {

      Map<String, Object> claims = Map.of(
         "type", entity.getStatus().getName(),
         "id", entity.getId()
      );

      var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      var subject = entity.getUsername();


      return Jwts.builder()
         .setClaims(claims)
         .setSubject(subject)
         .signWith(key, HS256)
         .setIssuedAt(new Date())
         .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
         .compact();
   }


   public void validateSignature(String token){
      try {
         Jwts.parserBuilder()
            .setSigningKey(secret.getBytes())
            .build()
            .parseClaimsJws(token);
      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
      }
   }

}
