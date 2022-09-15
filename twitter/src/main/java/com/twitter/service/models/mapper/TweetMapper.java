package com.twitter.service.models.mapper;

import com.twitter.service.models.dtos.PostRequest;
import com.twitter.service.models.dtos.PostResponse;
import com.twitter.service.models.entity.TweetEntity;

import java.util.Date;

public class TweetMapper {

   public static TweetEntity dtoToEntity(PostRequest post, Long id) {
      return TweetEntity.builder()
         .userId(id)
         .date(new Date())
         .content(post.getContent())
         .build();
   }

   public static PostResponse entityToDto(TweetEntity entity) {
      return PostResponse.builder()
         .postId(entity.getId())
         .userId(entity.getUserId())
         .content(entity.getContent())
         .date(entity.getDate())
         .build();
   }


}
