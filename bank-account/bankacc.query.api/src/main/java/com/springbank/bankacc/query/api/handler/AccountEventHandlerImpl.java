package com.springbank.bankacc.query.api.handler;

import com.springbank.bankacc.core.event.AccountClosedEvent;
import com.springbank.bankacc.core.event.AccountOpenedEvent;
import com.springbank.bankacc.core.event.FundsDepositedEvent;
import com.springbank.bankacc.core.event.FundsWithdrawnEvent;
import com.springbank.bankacc.core.model.BankAccount;
import com.springbank.bankacc.query.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("bankaccount-group")
@RequiredArgsConstructor
public class AccountEventHandlerImpl implements AccountEventHandler {

  private final AccountRepository accountRepository;

  @EventHandler
  @Override
  public void on(AccountOpenedEvent event) {
    var bankAccount =
        BankAccount.builder()
            .id(event.getId())
            .accountHolderId(event.getAccountHolderId())
            .creationDate(event.getCreationDate())
            .accountType(event.getAccountType())
            .balance(event.getOpeningBalance())
            .build();
    accountRepository.save(bankAccount);
  }

  @EventHandler
  @Override
  public void on(FundsDepositedEvent event) {
    var optionalBankAccount = accountRepository.findById(event.getId());
    if (optionalBankAccount.isPresent()) {
      var backAccount = optionalBankAccount.get();
      backAccount.setBalance(event.getBalance());
      accountRepository.save(backAccount);
    }
  }

  @EventHandler
  @Override
  public void on(FundsWithdrawnEvent event) {
    var optionalBankAccount = accountRepository.findById(event.getId());
    if (optionalBankAccount.isPresent()) {
      var bankAccount = optionalBankAccount.get();
      bankAccount.setBalance(event.getBalance());
      accountRepository.save(bankAccount);
    }
  }

  @EventHandler
  @Override
  public void on(AccountClosedEvent event) {
    accountRepository.deleteById(event.getId());
  }
}
