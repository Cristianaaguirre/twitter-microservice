package com.twitter.service.repository;

import com.twitter.service.models.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, String> {
}
