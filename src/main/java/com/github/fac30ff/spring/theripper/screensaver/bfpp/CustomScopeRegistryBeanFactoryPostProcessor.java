package com.github.fac30ff.spring.theripper.screensaver.bfpp;

import com.github.fac30ff.spring.theripper.screensaver.scope.PeriodicalScopeConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomScopeRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    beanFactory.registerScope("periodical", new PeriodicalScopeConfigurer());
  }
}
