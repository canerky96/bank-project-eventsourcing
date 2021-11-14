package com.kaya.user.query.api.handler;

import com.kaya.user.core.event.UserRegisteredEvent;
import com.kaya.user.core.event.UserRemovedEvent;
import com.kaya.user.core.event.UserUpdatedEvent;

public interface UserEventHandler {

  void on(UserRegisteredEvent event);

  void on(UserUpdatedEvent event);

  void on(UserRemovedEvent event);
}
