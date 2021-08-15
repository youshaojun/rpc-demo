package com.example.consumer;

import com.example.rpc.annotation.EnableMultiRedisTemplate;
import com.example.rpc.annotation.MapperScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * 消费者
 */
@Slf4j
@SpringBootApplication
@ComponentScan("com.example")
@EnableMultiRedisTemplate
@MapperScan({"com.example.rpc.registry.mapper"})
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Value("${current.app.config}")
	private String currentAppConfig;

	@PostConstruct
	public void version(){
		log.info("=============== 当前应用配置为[ " + currentAppConfig + " ] =================");
	}


}
