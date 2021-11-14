package com.kaya.user.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cqrs_user")
public class User {
  @Id private String id;

  @NotEmpty(message = "Firstname is mandatory")
  private String firstname;

  @NotEmpty(message = "Lastname is mandatory")
  private String lastname;

  @Email(message = "Please provide a valid e-mail address")
  private String emailAddress;

  @NotNull(message = "Please provide an account credentials")
  private Account account;
}
