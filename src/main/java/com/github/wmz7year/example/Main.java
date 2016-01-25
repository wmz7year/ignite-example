package com.github.wmz7year.example;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteSpring;
import org.apache.ignite.cache.CachePeekMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Main {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		Ignite ignite = IgniteSpring.start(context);
		RuntimeBeanManager runtimeBeanManager = context.getBean(RuntimeBeanManager.class);
		IgniteCache<String, Entity> cache = ignite.getOrCreateCache("testCache");
		Entity entity = null;
		if (cache.size(CachePeekMode.ALL) != 0) {
			entity = cache.get("a");
		} else {
			entity = runtimeBeanManager.createEntry("a");
			cache.put("a", entity);
		}
		entity.doServiceMethod();
	}
}
