/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kisoft.qahwagi.event;

import org.apache.commons.lang3.StringUtils;
import org.dizitart.jbus.Subscribe;

/**
 *
 * @author tareq
 */
public abstract class DomainEventHandler<T> {

  @Subscribe(async = true)
  public void handleEvent(DomainEvent event) {
    if (StringUtils.equals(getEventName(), event.getEventName())) {
      if (event.getEventData() != null) {
        doHandle(event.getAsClass(getType()));
      } else {
        doHandle(null);
      }
    }
  }

  public abstract String getEventName();

  public abstract void doHandle(T eventData);

  public abstract Class<T> getType();

}
