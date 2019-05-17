package com.github.fac30ff.spring.theripper.screensaver.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PeriodicalScopeConfigurer implements Scope {
  private Map<String, Pair<LocalTime, Object>> map = new HashMap<>();

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    if(map.containsKey(name)) {
      Pair<LocalTime, Object> pair = map.get(name);
      int secondsSinceLastRequest = LocalTime.now().getSecond() - pair.getKey().getSecond();
      if(secondsSinceLastRequest>5) {
        map.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));
      }
    } else {
      map.put(name, new Pair<>(LocalTime.now(), objectFactory.getObject()));
    }
    return map.get(name).getValue();
  }

  @Override
  public Object remove(String s) {
    return null;
  }

  @Override
  public void registerDestructionCallback(String s, Runnable runnable) {

  }

  @Override
  public Object resolveContextualObject(String s) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }

  private final class Pair<T, U> {
    private final T t;
    private final U u;

    Pair(T t, U u) {
      this.t = t;
      this.u = u;
    }

    T getKey() {
      return this.t;
    }
    U getValue() {
      return this.u;
    }
  }
}
