package com.willowtree.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableFeignClients
//@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class NameGameSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(NameGameSvcApplication.class, args);
	}

}
