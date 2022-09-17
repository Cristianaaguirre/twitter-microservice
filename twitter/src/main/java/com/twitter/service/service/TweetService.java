package com.twitter.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.service.models.dtos.PostRequest;
import com.twitter.service.models.dtos.PostResponse;

public interface TweetService {

   PostResponse postTweet(PostRequest post, String token);
   PostResponse updateTweet(PostRequest post, String id);
   void deleteTweet(String id);
}
