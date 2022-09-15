package com.timeline.service.controller;

import com.timeline.service.service.UserTimelineBuilderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeline")
public class TimelineController {

   private final UserTimelineBuilderImpl service;

   @GetMapping
   public ResponseEntity<?> getUserTimeline(@RequestParam Integer obj)  {
      return ResponseEntity.ok(service.findAllByUserId(1L));
   }


}
