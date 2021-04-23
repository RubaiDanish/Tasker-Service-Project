package com.example.taskereurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskerEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskerEurekaServerApplication.class, args);
	}

}
