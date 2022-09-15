package com.gateway.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class GatewayConfig {

   @Bean
   public RouteLocator routeConfig(RouteLocatorBuilder builder) {
      return builder.routes()
         .route(r -> r.path("/tweet/**")
            .uri("lb://post-service"))
         .route(r -> r.path("/timeline/**")
            .uri("lb://timeline-service"))
         .build();
   }

//   @Bean
//   @Profile("localhost-eureka-cb-filter")
//   public RouteLocator configLocalEurekaCbWithFilter(RouteLocatorBuilder builder) {
//      return builder.routes()
//         .route(r -> r.path("/dragonball/*")
//            .filters(f -> {
//               f.circuitBreaker(
//                  c -> c.setName("failOverCb")
//                     .setFallbackUri("forward:/dragonball-fo/character")
//                     .setRouteId("DBFo"));
//               f.filters(filter);
//               return f;
//            })
//            .uri("lb://devs4j-dragon-ball"))
//         .route(r -> r.path("/game-of-thrones/*")
//            .uri("lb://devs4j-game-of-thrones"))
//         .route(r -> r.path("/dragonball-fo/*")
//            .uri("lb://devs4j-dragon-ball-failover"))
//         .route(r -> r.path("/auth/**")
//            .uri("lb://devs4j-auth"))
//         .build();
//   }
}
