package com.enrique.xmlgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.enrique.xmlgateway","com.enrique.xmlgateway.controller", "com.enrique.xmlservice"})
public class XmlGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlGatewayApplication.class, args);
	}

}
