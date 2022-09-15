package com.timeline.service.service;

import com.timeline.service.models.dto.TweetDTO;

import java.util.List;

public interface UserTimelineBuilder {
   List<TweetDTO> findAllByUserId(Long userId);
}
