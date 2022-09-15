package com.auth.configuration.repository;

import com.auth.configuration.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   boolean existsByUsername(String username);
}
