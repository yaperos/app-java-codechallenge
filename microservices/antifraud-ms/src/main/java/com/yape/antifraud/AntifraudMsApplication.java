package com.yape.antifraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class})
public class AntifraudMsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AntifraudMsApplication.class, args);
	}

}
