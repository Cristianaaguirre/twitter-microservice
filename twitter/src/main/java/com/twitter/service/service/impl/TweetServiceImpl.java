package com.twitter.service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.service.config.TopicsName;
import com.twitter.service.models.dtos.PostRequest;
import com.twitter.service.models.dtos.PostResponse;
import com.twitter.service.models.mapper.TweetMapper;
import com.twitter.service.repository.TweetRepository;
import com.twitter.service.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

   private final TweetRepository repository;
   private final StreamBridge bridge;

   @Transactional
   public PostResponse postTweet(PostRequest post) throws JsonProcessingException {
      var tweet = TweetMapper.dtoToEntity(post, 1L);
      repository.save(tweet);

      var message = buildMessage(tweet);
      var topic = TopicsName.getTopic("created");

      bridge.send(topic, message);

      return TweetMapper.entityToDto(tweet);
   }

   @Transactional
   public PostResponse updateTweet(PostRequest post, String id) {

      var tweet = repository.findById(id)
         .orElseThrow(() -> new EntityNotFoundException("Tweet not found"));

      tweet.setContent(post.getContent());
      repository.save(tweet);

      return TweetMapper.entityToDto(tweet);
   }


   @Transactional
   public void deleteTweet(String id) {
      repository.deleteById(id);
   }


   private Message<Object> buildMessage(Object id) {
      return MessageBuilder
         .withPayload(id)
         .build();
   }

}
