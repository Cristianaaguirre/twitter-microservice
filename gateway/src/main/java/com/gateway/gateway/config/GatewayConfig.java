package com.gateway.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class GatewayConfig {

   private final AuthFilter filter;

   public GatewayConfig(AuthFilter filter) {
      this.filter = filter;
   }

   @Bean
   public RouteLocator routeConfig(RouteLocatorBuilder builder) {
      return builder.routes()
         .route(r -> r.path("/tweet/**")
            .filters(f -> f.filter(filter))
            .uri("lb://twitter"))
         .route(r -> r.path("/timeline/**")
            .filters(f -> f.filter(filter))
            .uri("lb://timeline"))
         .route(r -> r.path("/auth/**")
            .uri("lb://authentication"))
         .build();
   }

}
