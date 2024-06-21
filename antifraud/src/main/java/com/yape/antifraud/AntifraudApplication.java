package com.yape.antifraud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yape.antifraud.subscriber.ConsumerAntifraud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AntifraudApplication {

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(AntifraudApplication.class, args);
		var consumer = ConsumerAntifraud.getInstance();
		consumer.start();
	}

}
