package com.gateway.gateway.config;

import com.gateway.gateway.models.TokenDTO;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class AuthFilter implements GatewayFilter {

   private final WebClient.Builder webClient;

   public AuthFilter(WebClient.Builder webClient) {
      this.webClient = webClient;
   }

   public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
      ServerHttpResponse response = exchange.getResponse();
      response.setStatusCode(status);
      return response.setComplete();
   }


   @Override
   public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

      if (!exchange.getRequest().getHeaders().containsKey(AUTHORIZATION))
         return onError(exchange, BAD_REQUEST);

      var token = exchange.getRequest().getHeaders().get(AUTHORIZATION).get(0);

      var chunks = token.split(" ");

      if (chunks.length != 2 && !chunks[0].equals("Bearer"))
         return onError(exchange, BAD_REQUEST);

      return webClient.build().post()
         .uri("http://authentication/auth/validate?token=" + chunks[1])
         .retrieve()
         .bodyToMono(TokenDTO.class)
         .map(t -> exchange)
         .flatMap(chain::filter);
   }
}
