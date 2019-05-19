package com.github.fac30ff.spring.theripper.bpp;

import com.github.fac30ff.spring.theripper.annotation.Profiling;
import com.github.fac30ff.spring.theripper.controller.ProfilingController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.WeakHashMap;

@Component
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {
  private Map<String, Class> originalBeans = new WeakHashMap<>();
  private ProfilingController controller = new ProfilingController();

  public ProfilingHandlerBeanPostProcessor() throws Exception {
    MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
    platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
  }

  public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
    Class<?> beanClass = bean.getClass();
    if (beanClass.isAnnotationPresent(Profiling.class)) {
      originalBeans.put(beanName, beanClass);
    }
    return bean;
  }

  public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
    Class beanClass = originalBeans.get(beanName);
    if (beanClass != null) {
      return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
        if (controller.isEnabled()) {
          System.out.println("profiling...");
          long before = System.nanoTime();
          Object retVal = method.invoke(bean, args);
          long after = System.nanoTime();
          System.out.println(after-before);
          System.out.println("done");
          return retVal;
        } else {
          return method.invoke(bean, args);
        }
      });
    }
    return bean;
  }
}
