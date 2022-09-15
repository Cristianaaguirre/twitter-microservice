package com.twitter.service.models.mapper;

import com.twitter.service.models.entity.FollowEntity;
import com.twitter.service.models.dtos.CelebrityAccount;

public class FollowMapper {

   public static FollowEntity generateFollow(Long id, Long follow) {
      return FollowEntity.builder()
         .myId(id)
         .followId(follow)
         .typeAccount(CelebrityAccount.NO)
         .build();
   }

}
