package com.example.consumer;

import com.example.rpc.annotation.EnableMultiRedisTemplate;
import com.example.rpc.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 消费者
 */
@SpringBootApplication
@ComponentScan("com.example")
@EnableMultiRedisTemplate
@MapperScan({"com.example.rpc.registry.mapper"})
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
