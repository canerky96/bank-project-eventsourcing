package com.springbank.bankacc.query.api.handler;

import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.query.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.query.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.query.FindAccountWithBalanceQuery;
import com.springbank.bankacc.query.api.query.FindAllAccountsQuery;
import com.springbank.bankacc.query.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountQueryHandlerImpl implements AccountQueryHandler {

  private final AccountRepository accountRepository;

  @QueryHandler
  @Override
  public AccountLookupResponse findAccountById(FindAccountByIdQuery query) {
    var bankAccount = accountRepository.findById(query.getId());
    return bankAccount
        .map(account -> new AccountLookupResponse(account, "Bank account"))
        .orElseGet(() -> new AccountLookupResponse("No bcank account"));
  }

  @QueryHandler
  @Override
  public AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query) {
    var bankAccount = accountRepository.findByAccountHolderId(query.getAccountHolderId());
    return bankAccount
        .map(account -> new AccountLookupResponse(account, "Bank account"))
        .orElseGet(() -> new AccountLookupResponse("No bank account"));
  }

  @QueryHandler
  @Override
  public AccountLookupResponse findAllAccounts(FindAllAccountsQuery query) {
    return null;
  }

  @QueryHandler
  @Override
  public AccountLookupResponse findAccountsWithBalance(FindAccountWithBalanceQuery query) {
    return null;
  }
}
