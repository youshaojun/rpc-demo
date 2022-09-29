package com.example.consumer.service;

import cn.hutool.extra.spring.SpringUtil;
import com.example.rpc.registry.DefaultExceptionHandlerAdviceBeanDefinitionRegistrar;
import com.example.rpc.registry.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/test")
@RestController
@Import(value = {SpringUtil.class, DefaultExceptionHandlerAdviceBeanDefinitionRegistrar.class})
@RestControllerAdvice
public class TestController {

	@ExceptionHandler(MyException.class)
	public String handlerException(MyException e) {
		log.error("error：{}", e.getMessage());
		return "error";
	}

	@GetMapping("/hello")
	public void hello() {
		TestController bean = SpringUtil.getBean(TestController.class);
		log.error("bean = [{}]", bean);
	}

}
