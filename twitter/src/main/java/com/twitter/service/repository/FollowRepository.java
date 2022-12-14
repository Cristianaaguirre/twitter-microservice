package com.twitter.service.repository;

import com.twitter.service.models.entity.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String> {

   Optional<FollowEntity> findByMyIdAndFollowId(Long myId, Long followId);

}
