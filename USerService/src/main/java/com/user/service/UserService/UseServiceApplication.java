package com.user.service.UserService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseServiceApplication.class, args);
	}

}
