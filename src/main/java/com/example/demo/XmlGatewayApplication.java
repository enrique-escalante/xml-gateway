package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo","com.example.demo.controller", "com.example.xmlservice"})
public class XmlGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlGatewayApplication.class, args);
	}

}
