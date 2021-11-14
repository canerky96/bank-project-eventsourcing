package com.kaya.user.cmd.api.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class RemoveUserCommand {

  @TargetAggregateIdentifier private String id;
}
