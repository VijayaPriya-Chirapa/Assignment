package com.Application1.in;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "com.Application1.in.mapper")
@SpringBootApplication
public class Application1Application {

	public static void main(String[] args) {
		SpringApplication.run(Application1Application.class, args);
	}

}
