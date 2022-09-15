package com.timeline.service.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TweetDTO {
   private String content;
   private Date date;
}
