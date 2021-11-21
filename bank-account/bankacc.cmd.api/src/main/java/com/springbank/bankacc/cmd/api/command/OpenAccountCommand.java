package com.springbank.bankacc.cmd.api.command;

import com.springbank.bankacc.core.model.AccountType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class OpenAccountCommand {
  @TargetAggregateIdentifier private String id;
  private String accountHolderId;
  private AccountType accountType;
  private double openingBalance;
}
