package com.twitter.service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.service.models.dtos.PostRequest;
import com.twitter.service.models.dtos.PostResponse;
import com.twitter.service.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

   private final TweetService service;

   @PostMapping
   public ResponseEntity<PostResponse> postTweet(@RequestBody PostRequest post) throws JsonProcessingException {
      return Optional.of(service.postTweet(post))
         .map(ResponseEntity::ok)
         .orElse(ResponseEntity.status(400).build());
   }

   @PutMapping("/{id}")
   public ResponseEntity<PostResponse> updateTweet(@PathVariable String id, @RequestBody PostRequest post) {
      return Optional.of(service.updateTweet(post, id))
         .map(ResponseEntity::ok)
         .orElse(ResponseEntity.status(400).build());
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteTweet(@PathVariable String id) {
      service.deleteTweet(id);
      return ResponseEntity.noContent().build();
   }
}
