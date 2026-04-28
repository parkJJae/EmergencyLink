package com.parkjjae.emergencylink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EmergencyLinkApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmergencyLinkApplication.class, args);
	}
}