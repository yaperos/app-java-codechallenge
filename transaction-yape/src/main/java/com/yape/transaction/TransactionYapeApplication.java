package com.yape.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TransactionYapeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionYapeApplication.class, args);
	}

}
