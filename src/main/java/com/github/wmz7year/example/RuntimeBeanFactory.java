package com.github.wmz7year.example;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RuntimeBeanFactory {

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public RuntimeBeanManager createRuntimeBeanManager() {
		return new RuntimeBeanManager() {

			/*
			 * @see
			 * com.github.wmz7year.example.RuntimeBeanManager#createEntry(java.
			 * lang.String)
			 */
			@Override
			public Entity createEntry(String field) {
				return createRuntimeEntity(field);
			}
		};
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Entity createRuntimeEntity(String field) {
		return new Entity(field);
	}
}
