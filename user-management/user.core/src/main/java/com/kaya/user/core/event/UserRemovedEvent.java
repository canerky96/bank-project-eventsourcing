package com.kaya.user.core.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRemovedEvent {
  private String id;
}
