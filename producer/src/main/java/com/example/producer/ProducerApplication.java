package com.example.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 生产者
 */
@SpringBootApplication
@ComponentScan("com.example")
public class ProducerApplication {

	public static void main(String[] args) {
		 SpringApplication.run(ProducerApplication.class, args);
	}

}
