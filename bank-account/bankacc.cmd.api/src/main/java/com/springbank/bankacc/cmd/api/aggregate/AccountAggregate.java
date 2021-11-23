package com.springbank.bankacc.cmd.api.aggregate;

import com.springbank.bankacc.cmd.api.command.CloseAccountCommand;
import com.springbank.bankacc.cmd.api.command.DepositFundsCommand;
import com.springbank.bankacc.cmd.api.command.OpenAccountCommand;
import com.springbank.bankacc.cmd.api.command.WithdrawFundsCommand;
import com.springbank.bankacc.core.event.AccountClosedEvent;
import com.springbank.bankacc.core.event.AccountOpenedEvent;
import com.springbank.bankacc.core.event.FundsDepositedEvent;
import com.springbank.bankacc.core.event.FundsWithdrawnEvent;
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

  @CommandHandler
  public void handle(DepositFundsCommand command) {
    var amount = command.getAmount();
    var event =
        FundsDepositedEvent.builder()
            .id(command.getId())
            .amount(command.getAmount())
            .balance(this.balance + amount)
            .build();
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(FundsDepositedEvent event) {
    this.balance += event.getAmount();
  }

  @CommandHandler
  public void handle(WithdrawFundsCommand command) {
    var amount = command.getAmount();
    if (this.balance - amount < 0) {
      throw new IllegalStateException("Withdrawal declined, insufficient funds!");
    }
    var event =
        FundsWithdrawnEvent.builder()
            .id(command.getId())
            .amount(command.getAmount())
            .balance(this.balance - amount)
            .build();
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(FundsWithdrawnEvent event) {
    this.balance -= event.getAmount();
  }

  @CommandHandler
  public void handle(CloseAccountCommand command) {
    var event = AccountClosedEvent.builder().id(command.getId()).build();
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(AccountClosedEvent event) {
    AggregateLifecycle.markDeleted();
  }
}
