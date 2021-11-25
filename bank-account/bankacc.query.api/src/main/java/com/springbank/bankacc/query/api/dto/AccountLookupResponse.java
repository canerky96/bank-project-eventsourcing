package com.springbank.bankacc.query.api.dto;

import com.springbank.bankacc.core.dto.BaseResponse;
import com.springbank.bankacc.core.model.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountLookupResponse extends BaseResponse {

  private List<BankAccount> accounts;

  public AccountLookupResponse(String message) {
    super(message);
  }

  public AccountLookupResponse(List<BankAccount> accounts, String message) {
    super(message);
    this.accounts = accounts;
  }

  public AccountLookupResponse(BankAccount account, String message) {
    super(message);
    this.accounts = new ArrayList<>();
    this.accounts.add(account);
  }

  public List<BankAccount> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<BankAccount> accounts) {
    this.accounts = accounts;
  }
}
