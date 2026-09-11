package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SumCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SumCalculatorApplication.class, args);
		System.out.println("Sum of 2 numbers");
	}

}
