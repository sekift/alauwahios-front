package cn.alauwahios.front.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class AlauwahiosSpringContext {

	private static final Logger logger = LoggerFactory
			.getLogger(AlauwahiosSpringContext.class);

	private static ApplicationContext applicationContext;

	static {
		logger.error("AlauwahiosSpringContext initial!");
		try {
			applicationContext = new FileSystemXmlApplicationContext(
					"resources/spring/spring-context.xml");
		} catch (Exception e) {
			logger.error("AlauwahiosSpringContext initial ERROR:", e);
		}
	}

	public static void initial() {
		// doing nothing
	}

	/**
	 * 根据bean的名称获取springbean
	 * 
	 * @param beanName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName) {
		try {
			return (T) applicationContext.getBean(beanName);
		} catch (NoSuchBeanDefinitionException ne) {
			// bean不存在
			return null;
		} catch (Exception e) {
			logger.error("[管舌鸟web]根据bean的名称获取springbean出错:", e);
		}
		return null;
	}

}
