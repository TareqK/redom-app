/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.event;

import org.dizitart.jbus.JBus;

/**
 *
 * @author tareq
 */
public class EventBus {

  private static EventBus instance = getInstance();

  private EventBus() {
    jbus = new JBus();
  }

  public static final EventBus getInstance() {
    if (instance == null) {
      instance = new EventBus();
    }
    return instance;
  }
  private final JBus jbus;

  public void post(DomainEvent event) {
    jbus.post(event);
  }

  public void subscribe(DomainEventHandler handler) {
    jbus.registerWeak(handler);
  }
}
