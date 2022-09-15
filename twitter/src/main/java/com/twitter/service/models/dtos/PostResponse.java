package com.twitter.service.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PostResponse {
   private String postId;
   private Long userId;
   private String content;
   private Date date;
}
