package com.twitter.service.models.entity;

import com.twitter.service.models.dtos.CelebrityAccount;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "follows")
public class FollowEntity {

   @Id
   @GeneratedValue(generator = "uuid")
   @GenericGenerator(name = "uuid", strategy = "uuid2")
   private String id;
   private Long myId;
   private Long followId;
   @Enumerated
   private CelebrityAccount typeAccount;

   public FollowEntity() {}


   public FollowEntity(String id, Long myId, Long followId, CelebrityAccount typeAccount) {
      this.id = id;
      this.myId = myId;
      this.followId = followId;
      this.typeAccount = typeAccount;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Long getMyId() {
      return myId;
   }

   public Long getFollowId() {
      return followId;
   }

   public void setFollowId(Long followId) {
      this.followId = followId;
   }

   public CelebrityAccount getTypeAccount() {
      return typeAccount;
   }

   public void setTypeAccount(CelebrityAccount typeAccount) {
      this.typeAccount = typeAccount;
   }
}
