package edu.iu.c322.assetmanagement.licensingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class LicensingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicensingserviceApplication.class, args);
	}


	//introduce a cache, which balances resources among multiple instances.

	@LoadBalanced
	//a class that follows conventions: private instance, public get/set w/ specific name
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
