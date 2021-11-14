package com.kaya.user.cmd.api.aggregates;

import com.kaya.user.cmd.api.command.RegisterUserCommand;
import com.kaya.user.cmd.api.command.RemoveUserCommand;
import com.kaya.user.cmd.api.command.UpdateUserCommand;
import com.kaya.user.cmd.api.security.PasswordEncoder;
import com.kaya.user.cmd.api.security.PasswordEncoderImpl;
import com.kaya.user.core.model.User;
import com.kaya.user.core.event.UserRegisteredEvent;
import com.kaya.user.core.event.UserRemovedEvent;
import com.kaya.user.core.event.UserUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {

  private final PasswordEncoder passwordEncoder;

  @AggregateIdentifier private String id;
  private User user;

  public UserAggregate() {
    passwordEncoder = new PasswordEncoderImpl();
  }

  @CommandHandler
  public UserAggregate(RegisterUserCommand command) {
    passwordEncoder = new PasswordEncoderImpl();
    var newUser = command.getUser();
    newUser.setId(command.getId());
    var password = command.getUser().getAccount().getPassword();
    var hashedPassword = passwordEncoder.hashPassword(password);
    newUser.getAccount().setPassword(hashedPassword);

    var event = UserRegisteredEvent.builder().id(command.getId()).user(command.getUser()).build();
    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(UpdateUserCommand command) {
    var updatedUser = command.getUser();
    updatedUser.setId(command.getId());
    var password = updatedUser.getAccount().getPassword();
    var hashedPassword = passwordEncoder.hashPassword(password);
    updatedUser.getAccount().setPassword(hashedPassword);

    var event =
        UserUpdatedEvent.builder().id(UUID.randomUUID().toString()).user(updatedUser).build();
    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(RemoveUserCommand command) {
    var event = UserRemovedEvent.builder().build();
    event.setId(command.getId());
    AggregateLifecycle.apply(event);
  }

  @EventHandler
  public void on(UserRegisteredEvent event) {
    this.id = event.getId();
    this.user = event.getUser();
  }

  @EventHandler
  public void on(UserUpdatedEvent event) {
    this.user = event.getUser();
  }

  @EventHandler
  public void on(UserRemovedEvent event) {
    AggregateLifecycle.markDeleted();
  }
}
