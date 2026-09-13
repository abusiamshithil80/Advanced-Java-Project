package com.supershop.SuperShopManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.supershop.SuperShopManagementSystem", "Api", "Service", "Repository", "Auth", "logging"})


public class SuperShopManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperShopManagementSystemApplication.class, args);
	}

}
