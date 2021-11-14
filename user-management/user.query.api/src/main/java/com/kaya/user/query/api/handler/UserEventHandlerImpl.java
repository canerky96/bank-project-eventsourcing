package com.kaya.user.query.api.handler;

import com.kaya.user.core.event.UserRegisteredEvent;
import com.kaya.user.core.event.UserRemovedEvent;
import com.kaya.user.core.event.UserUpdatedEvent;
import com.kaya.user.query.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

  private final UserRepository userRepository;

  @EventHandler
  @Override
  public void on(UserRegisteredEvent event) {
    userRepository.save(event.getUser());
  }

  @EventHandler
  @Override
  public void on(UserUpdatedEvent event) {
    userRepository.save(event.getUser());
  }

  @EventHandler
  @Override
  public void on(UserRemovedEvent event) {
    userRepository.deleteById(event.getId());
  }
}
