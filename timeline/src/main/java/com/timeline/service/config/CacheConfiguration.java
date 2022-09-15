package com.timeline.service.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {


   @Bean(destroyMethod = "shutdown")
   public RedissonClient redisson() {
      var config = new Config();
      config.useSingleServer().setAddress("redis://127.0.0.1:6379");
      return Redisson.create(config);
   }

   @Bean
   public CacheManager getManager(RedisConnectionFactory client) {
       var cacheConfig = Map.of(
          "post", createConfig(1, ChronoUnit.MINUTES),
          "follow", createConfig(1, ChronoUnit.MINUTES)
       );
       return RedisCacheManager
          .builder(client)
          .withInitialCacheConfigurations(cacheConfig)
          .build();
   }

   private RedisCacheConfiguration createConfig(long time, ChronoUnit temporal) {
      return RedisCacheConfiguration.defaultCacheConfig()
         .entryTtl(Duration.of(time, temporal));
   }
}
