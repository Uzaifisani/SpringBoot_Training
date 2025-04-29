package com.example.SpringMailerDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringMailerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMailerDemoApplication.class, args);
	}

}
