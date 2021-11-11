package com.kaya.user.cmd.api.command;

import com.kaya.user.core.config.model.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RegisterUserCommand {

  @TargetAggregateIdentifier private String id;
  private User user;
}
