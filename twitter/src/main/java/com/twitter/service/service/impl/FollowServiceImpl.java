package com.twitter.service.service.impl;

import com.twitter.service.models.mapper.FollowMapper;
import com.twitter.service.repository.FollowRepository;
import com.twitter.service.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

   private final FollowRepository repository;
   private final StreamBridge bridge;


   public void follow(Long myId, Long followId) {
      var entity = FollowMapper.generateFollow(myId, followId);
      repository.save(entity);

      var map = Map.of();

   }

   public void unfollow(Long myId, Long unfollowId) {
      repository.findByMyIdAndFollowId(myId, unfollowId)
         .ifPresent(
            f -> {

            }
         );
   }

}
