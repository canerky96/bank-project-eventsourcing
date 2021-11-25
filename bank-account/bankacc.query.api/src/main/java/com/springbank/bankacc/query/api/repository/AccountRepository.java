package com.springbank.bankacc.query.api.repository;

import com.springbank.bankacc.core.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<BankAccount, String> {
  Optional<BankAccount> findByAccountHolderId(String accountHolderId);
}
