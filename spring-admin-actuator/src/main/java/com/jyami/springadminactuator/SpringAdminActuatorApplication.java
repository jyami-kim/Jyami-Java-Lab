package com.jyami.springadminactuator;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringAdminActuatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringAdminActuatorApplication.class, args);
	}
}
