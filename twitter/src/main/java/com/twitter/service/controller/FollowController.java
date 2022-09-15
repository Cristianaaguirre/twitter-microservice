package com.twitter.service.controller;

import com.twitter.service.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {

   private final FollowService service;

   @PostMapping
   public ResponseEntity<?> follow(@RequestParam Long myId, @RequestParam Long user) {
      service.follow(myId, user);
      return ResponseEntity.status(204).build();
   }

   @DeleteMapping
   public ResponseEntity<?> unFollow(@RequestParam Long myId, @RequestParam Long user) {
      service.unfollow(myId, user);
      return ResponseEntity.status(204).build();
   }

}
