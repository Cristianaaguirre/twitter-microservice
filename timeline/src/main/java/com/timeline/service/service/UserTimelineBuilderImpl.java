package com.timeline.service.service;

import com.timeline.service.models.dto.TweetDTO;
import com.timeline.service.models.entity.TweetEntity;
import com.timeline.service.models.mapper.TweetMapper;
import com.timeline.service.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserTimelineBuilderImpl implements UserTimelineBuilder {

   private final TweetRepository repository;
   private final CacheManager manager;

   @Cacheable(cacheNames = "post", key = "#userId")
   public List<TweetDTO> findAllByUserId(Long userId) {
      return repository.findAllByUserId(userId)
         .stream()
         .map(TweetMapper::entityToDTO)
         .collect(Collectors.toList());
   }


   @Bean
   public Consumer<Message<TweetEntity>> userTimelineBuilder() {
      return c -> {

      };

   }


}
