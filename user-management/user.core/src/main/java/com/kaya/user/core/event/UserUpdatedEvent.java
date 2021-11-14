package com.kaya.user.core.event;

import com.kaya.user.core.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {
  private String id;
  private User user;
}
