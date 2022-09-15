package com.auth.configuration.models.entity;

import com.auth.configuration.models.mapper.TypeStatus;
import lombok.Builder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Table(name = "users")
public class UserEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String password;
   private TypeStatus status;

   public UserEntity() {}

   public UserEntity(Long id, String username, String password, TypeStatus status) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.status = status;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public TypeStatus getStatus() {
      return status;
   }

   public void setStatus(TypeStatus status) {
      this.status = status;
   }
}
