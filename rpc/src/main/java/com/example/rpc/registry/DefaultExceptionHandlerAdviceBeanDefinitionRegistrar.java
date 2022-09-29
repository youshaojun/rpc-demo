package com.example.rpc.registry;

import cn.hutool.core.util.ReflectUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.beans.Introspector;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class DefaultExceptionHandlerAdviceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> beansWithAnnotation = ((DefaultListableBeanFactory) registry).getBeansWithAnnotation(ControllerAdvice.class);
		boolean anyMatch = beansWithAnnotation.entrySet().stream()
			.flatMap(e -> Arrays.stream(ReflectUtil.getMethods(e.getValue().getClass())))
			.anyMatch(e -> Optional.ofNullable(e.getDeclaredAnnotation(ExceptionHandler.class))
				.map(ExceptionHandler::value)
				.map(val -> val[0])
				.map(val -> val == Exception.class)
				.orElse(false));
		if (anyMatch) {
			log.info("加载自定义异常处理器");
		} else {
			log.info("加载默认异常处理器");
			GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
			Class<DefaultExceptionHandlerAdvice> beanClass = DefaultExceptionHandlerAdvice.class;
			beanDefinition.setBeanClass(beanClass);
			registry.registerBeanDefinition(Introspector.decapitalize(ClassUtils.getShortName(beanClass)), beanDefinition);
		}
	}
}