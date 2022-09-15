package com.timeline.service.models.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
public class TweetEntity {
   @Id
   private String id;
   private Long userId;
   private Date date;
   private String content;
}
