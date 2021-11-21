package com.springbank.bankacc.cmd.api.aggregate;

import com.springbank.bankacc.cmd.api.command.OpenAccountCommand;
import com.springbank.bankacc.core.event.AccountOpenedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
  @AggregateIdentifier private String id;
  private String accountHolderId;
  private double balance;

  @CommandHandler
  public AccountAggregate(OpenAccountCommand openAccountCommand) {
    var event =
        AccountOpenedEvent.builder()
            .id(openAccountCommand.getId())
            .accountHolderId(openAccountCommand.getAccountHolderId())
            .accountType(openAccountCommand.getAccountType())
            .creationDate(new Date())
            .openingBalance(openAccountCommand.getOpeningBalance())
            .build();
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(AccountOpenedEvent event) {
    this.id = event.getId();
    this.accountHolderId = event.getAccountHolderId();
    this.balance = event.getOpeningBalance();
  }

}
