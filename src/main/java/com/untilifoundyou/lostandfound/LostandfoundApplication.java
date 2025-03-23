package com.untilifoundyou.lostandfound;

import com.untilifoundyou.lostandfound.item.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LostandfoundApplication {

	private static final Logger log = LoggerFactory.getLogger(LostandfoundApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LostandfoundApplication.class, args);
		log.info("Application started successfully.");

		//Run run = new Run(69, "tanginamo", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), Location.Indoor);
	}
	@Bean
	CommandLineRunner runner(){
		return args -> {
			Item items = new Item(69, "my will to live", LocalDateTime.now(), LocalDateTime.now().minus(1, ChronoUnit.HOURS), ItemStatus.Lost);
			log.info("Run: "+items);
		};
	}
}
