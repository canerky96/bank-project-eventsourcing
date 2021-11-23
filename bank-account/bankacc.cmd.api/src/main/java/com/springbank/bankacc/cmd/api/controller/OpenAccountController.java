package com.springbank.bankacc.cmd.api.controller;

import com.springbank.bankacc.cmd.api.command.OpenAccountCommand;
import com.springbank.bankacc.cmd.api.dto.OpenAccountResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/open-bank-account")
@RequiredArgsConstructor
public class OpenAccountController {

  private final CommandGateway commandGateway;

  @PostMapping
  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  public ResponseEntity<OpenAccountResponse> openAccount(
      @Validated @RequestBody OpenAccountCommand command) {
    var id = UUID.randomUUID().toString();
    command.setId(id);
    commandGateway.send(command);
    return new ResponseEntity<>(
        new OpenAccountResponse(id, "Successfully opened a new bank account!"), HttpStatus.CREATED);
  }
}
