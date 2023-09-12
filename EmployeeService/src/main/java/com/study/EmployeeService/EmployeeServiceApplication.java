package com.study.EmployeeService;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

	@Bean
	public MessageChannel ManageBook() {
		return MessageChannels.direct().get();
	}
//	@Autowired
//	public void configure(EventProcessingConfigurer configurer){
//		configurer.registerListenerInvocationErrorHandler(
//				"book",
//				configuration -> new BookServiceExceptionErrorhandler()
//		);
//	}

}
