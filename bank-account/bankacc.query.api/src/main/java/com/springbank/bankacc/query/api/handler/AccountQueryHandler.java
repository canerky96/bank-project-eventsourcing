package com.springbank.bankacc.query.api.handler;

import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.query.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.query.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.query.FindAccountWithBalanceQuery;
import com.springbank.bankacc.query.api.query.FindAllAccountsQuery;

public interface AccountQueryHandler {
  AccountLookupResponse findAccountById(FindAccountByIdQuery query);

  AccountLookupResponse findAccountByHolderId(FindAccountByHolderIdQuery query);

  AccountLookupResponse findAllAccounts(FindAllAccountsQuery query);

  AccountLookupResponse findAccountsWithBalance(FindAccountWithBalanceQuery query);
}
