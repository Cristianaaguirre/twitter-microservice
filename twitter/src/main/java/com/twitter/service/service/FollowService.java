package com.twitter.service.service;

public interface FollowService {
   void follow(Long userId, Long follow);
   void unfollow(Long userId, Long unfollow);
}
