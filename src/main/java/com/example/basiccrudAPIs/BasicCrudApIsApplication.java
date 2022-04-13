package com.example.basiccrudAPIs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class BasicCrudApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicCrudApIsApplication.class, args);
	}

}
