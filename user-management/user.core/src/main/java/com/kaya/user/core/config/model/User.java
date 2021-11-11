package com.kaya.user.core.config.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cqrs_user")
public class User {
  @Id private String id;
  private String firstname;
  private String lastname;
  private String emailAddress;
  private Account account;
}
