package com.github.fac30ff.spring.theripper.bfpp;

import com.github.fac30ff.spring.theripper.annotation.DeprecatedClass;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
      String beanClassName = beanDefinition.getBeanClassName();
      try {
        Class<?> beanClass = Class.forName(beanClassName);
        DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
        if (annotation != null) {
          beanDefinition.setBeanClassName(annotation.newImpl().getName());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
