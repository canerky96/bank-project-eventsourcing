package com.springbank.bankacc.query.api.handler;

import com.springbank.bankacc.core.event.AccountClosedEvent;
import com.springbank.bankacc.core.event.AccountOpenedEvent;
import com.springbank.bankacc.core.event.FundsDepositedEvent;
import com.springbank.bankacc.core.event.FundsWithdrawnEvent;

public interface AccountEventHandler {
  void on(AccountOpenedEvent event);

  void on(FundsDepositedEvent event);

  void on(FundsWithdrawnEvent event);

  void on(AccountClosedEvent event);
}
