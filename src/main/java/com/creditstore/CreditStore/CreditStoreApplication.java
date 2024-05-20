package com.creditstore.CreditStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CreditStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditStoreApplication.class, args);
	}

}
