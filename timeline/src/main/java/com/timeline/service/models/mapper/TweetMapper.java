package com.timeline.service.models.mapper;

import com.timeline.service.models.dto.TweetDTO;
import com.timeline.service.models.entity.TweetEntity;

public class TweetMapper {

   public static TweetDTO entityToDTO(TweetEntity entity) {
      return TweetDTO.builder()
         .content(entity.getContent())
         .date(entity.getDate())
         .build();
   }
}
