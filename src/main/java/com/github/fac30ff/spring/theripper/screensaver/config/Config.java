package com.github.fac30ff.spring.theripper.screensaver.config;

import com.github.fac30ff.spring.theripper.screensaver.ColorFrame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.awt.*;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "com.github.fac30ff.spring.theripper.screensaver")
public class Config {
  @Bean
  @Scope(value = "periodical"/*, proxyMode = ScopedProxyMode.TARGET_CLASS*/)
  public Color color() {
    Random random = new Random();
    return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
  }

  @Bean
  public ColorFrame frame() {
    return new ColorFrame() {
      @Override
      protected Color getColor() {
        return color();
      }
    };
  }

  public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    while (true) {
      context.getBean(ColorFrame.class).showOnRandomPlace();
      Thread.sleep(500);
    }
  }
}
