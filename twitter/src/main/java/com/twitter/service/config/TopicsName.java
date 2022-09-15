package com.twitter.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;


public class TopicsName {

   private static List<String> TOPICS;

   public static String getTopic(String topic) {
      return TOPICS.stream()
         .filter(t -> t.equalsIgnoreCase(topic))
         .collect(Collectors.joining());
   }

   public static void setTOPICS(List<String> topics) {
      TopicsName.TOPICS = topics;
   }

   @Component
   public static class SetTopicsName {

      @Value("${topics}")
      private List<String> topics;

      @PostConstruct
      public void init() {
         TopicsName.setTOPICS(topics);
      }

   }

}
