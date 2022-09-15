package com.timeline.service.repository;

import com.timeline.service.models.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, String> {

   List<TweetEntity> findAllByUserId(Long userId);

}
