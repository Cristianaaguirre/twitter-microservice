package com.github.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ProyectConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectConfigApplication.class, args);
	}

}
