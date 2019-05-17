package com.github.fac30ff.spring.theripper.quoter;

import com.github.fac30ff.spring.theripper.annotation.DeprecatedClass;
import com.github.fac30ff.spring.theripper.annotation.InjectRandomInt;
import com.github.fac30ff.spring.theripper.annotation.PostProxy;
import com.github.fac30ff.spring.theripper.annotation.Profiling;

import javax.annotation.PostConstruct;

@Profiling
@DeprecatedClass(newImpl = T1000.class)
public class TerminatorQuoter implements Quoter {
  @InjectRandomInt(min = 2, max = 8)
  private int repeat;
  private String message;

  public TerminatorQuoter() {
    System.out.println("Phase 1 constructor - java constructor");
    System.out.println("repeat = " + repeat);
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @PostConstruct
  public void init() {
    System.out.println("Phase 2 constructor - post construct");
    System.out.println("repeat = " + repeat);
  }

  /**
   * with @lazy && prototype do not work only with singleton
   */
  @PostProxy
  public void sayQuote() {
    System.out.println("Phase 3 constructor - context event listener");
    for (int i = 0; i < repeat; i++) {
      System.out.println("message = " + message);
    }
  }
}
