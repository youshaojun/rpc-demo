package com.example.rpc.registry;

import com.example.rpc.annotation.MapperScan;
import com.example.rpc.registry.factorybean.MyMapperFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Set;

/**
 * 扫描mapper, 注册BeanDefinition
 *
 * @author yousj
 * @since 2021/8/7
 */
public class MyMapperScannerRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		doScan(registry, importingClassMetadata);
	}

	/**
	 * 扫描获取需要整合的类
	 */
	private void doScan(BeanDefinitionRegistry registry, AnnotationMetadata importingClassMetadata) {
		AnnotationAttributes mapperScanAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName()));
		if (mapperScanAttrs != null) {
			ClassPathMapperScan scanner = new ClassPathMapperScan(registry);
			scanner.doScan(mapperScanAttrs.getStringArray("value"));
		}
	}

	/**
	 * ClassPathMapperScanner 直接从Mybatis中将关键代码复制过来
	 * 注意这里将源码中的许多代码删掉了
	 */
	public class ClassPathMapperScan extends ClassPathBeanDefinitionScanner {

		ClassPathMapperScan(BeanDefinitionRegistry registry) {
			super(registry, false);
		}

		@Override
		public Set<BeanDefinitionHolder> doScan(String... basePackages) {
			addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
			Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
			if (!beanDefinitions.isEmpty()) {
				processBeanDefinitions(beanDefinitions);
			}
			return beanDefinitions;
		}

		private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
			GenericBeanDefinition definition;
			for (BeanDefinitionHolder holder : beanDefinitions) {
				definition = (GenericBeanDefinition) holder.getBeanDefinition();
				String beanClassName = definition.getBeanClassName();
				definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
				definition.setBeanClass(MyMapperFactoryBean.class);
				definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
			}
		}

		@Override
		protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
			return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
		}

		@Override
		protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) {
			return super.checkCandidate(beanName, beanDefinition);
		}
	}
}
