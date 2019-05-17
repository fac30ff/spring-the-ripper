package com.github.fac30ff.spring.theripper;

import com.github.fac30ff.spring.theripper.quoter.Quoter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
  public static void main(String[] args) throws InterruptedException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("contex.xml");
    /*while (true) {
      Thread.sleep(500);
      context.getBean(Quoter.class).sayQuote();
    }*/
    context.getBean(Quoter.class).sayQuote();
  }
}
