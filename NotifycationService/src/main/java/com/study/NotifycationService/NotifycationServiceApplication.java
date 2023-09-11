package com.study.NotifycationService;

import org.apache.tomcat.util.net.WriteBuffer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

@SpringBootApplication
@RestController
public class NotifycationServiceApplication {
	@Bean
	public Consumer<String> handleMessage() {
		return message -> {
			//handle event here
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(NotifycationServiceApplication.class, args);
	}

}
