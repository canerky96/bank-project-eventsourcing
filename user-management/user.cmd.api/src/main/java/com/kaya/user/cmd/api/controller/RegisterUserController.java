package com.kaya.user.cmd.api.controller;

import com.kaya.user.cmd.api.command.RegisterUserCommand;
import com.kaya.user.cmd.api.dto.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/register-user")
@RequiredArgsConstructor
public class RegisterUserController {

  private final CommandGateway commandGateway;

  @PostMapping
  public ResponseEntity<RegisterUserResponse> registerUser(
      @Validated @RequestBody RegisterUserCommand command) {
    command.setId(UUID.randomUUID().toString());

    try {
      commandGateway.send(command);
      return new ResponseEntity<>(
          new RegisterUserResponse("User successfully registered!"), HttpStatus.CREATED);
    } catch (Exception e) {
      var safeErrorMessage =
          "Error while processing register user request with id - " + command.getId();
      return new ResponseEntity<>(
          new RegisterUserResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
