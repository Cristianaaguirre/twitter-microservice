package com.twitter.service.models.entity;

import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Builder
@Table(name = "tweets")
public class TweetEntity {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;

   private Long userId;

   private Date date;

   private String content;


   public TweetEntity() {}

   public TweetEntity(String id, Long userId, Date date, String content) {
      this.id = id;
      this.userId = userId;
      this.date = date;
      this.content = content;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) { this.userId = userId; }

   public Date getDate() {
      return date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof TweetEntity)) return false;
      TweetEntity that = (TweetEntity) o;
      return Objects.equals(id, that.id)
         && Objects.equals(userId, that.userId)
         && Objects.equals(date, that.date)
         && Objects.equals(content, that.content);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}
