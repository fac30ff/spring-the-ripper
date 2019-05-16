package com.github.fac30ff.spring.theripper.controller;

public class ProfilingController implements ProfilingControllerMBean {
  private boolean enabled = true;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
