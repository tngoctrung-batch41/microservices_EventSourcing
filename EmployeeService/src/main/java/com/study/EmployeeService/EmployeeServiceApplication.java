package com.study.EmployeeService;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

//	@Autowired
//	public void configure(EventProcessingConfigurer configurer){
//		configurer.registerListenerInvocationErrorHandler(
//				"book",
//				configuration -> new BookServiceExceptionErrorhandler()
//		);
//	}

}
