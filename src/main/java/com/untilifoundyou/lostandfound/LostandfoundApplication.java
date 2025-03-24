package com.untilifoundyou.lostandfound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LostandfoundApplication {

	private static final Logger log = LoggerFactory.getLogger(LostandfoundApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LostandfoundApplication.class, args);
		log.info("Application started successfully.");

	}
}
