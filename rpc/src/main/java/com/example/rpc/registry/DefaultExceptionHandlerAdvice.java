package com.example.rpc.registry;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
@NoArgsConstructor
public class DefaultExceptionHandlerAdvice {
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandlerAdvice.class);

	@ExceptionHandler({Exception.class})
	public String defaultExceptionHandler(Exception ex) {
		log.error(ex.getMessage(), ex);
		// ...... 略
		return "";
	}
}