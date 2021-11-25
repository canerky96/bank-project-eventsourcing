package com.springbank.bankacc.query.api.query;

import com.springbank.bankacc.query.api.dto.EqualityType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountWithBalanceQuery {
  private EqualityType equalityType;
  private double balance;
}
