package com.school.app.backoffice_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class BackofficeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackofficeServiceApplication.class, args);
	}

}
