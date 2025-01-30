package com.platzi.pizzeria_presto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PizzeriaPrestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaPrestoApplication.class, args);
	}

}
