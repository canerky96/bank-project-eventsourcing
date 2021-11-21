package com.springbank.bankacc.core.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountClosedEvent {
  private String id;
}
