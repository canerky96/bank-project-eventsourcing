package com.springbank.bankacc.cmd.api.controller;

import com.springbank.bankacc.cmd.api.command.WithdrawFundsCommand;
import com.springbank.bankacc.core.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/withdraw-funds")
@RequiredArgsConstructor
public class WithdrawFundsController {

  private final CommandGateway commandGateway;

  @PutMapping("{id}")
  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  public ResponseEntity<BaseResponse> withdrawFunds(
      @PathVariable("id") String id, @Validated @RequestBody WithdrawFundsCommand command) {
    command.setId(id);
    commandGateway.send(command);
    return new ResponseEntity<>(new BaseResponse("Funds successfully withdrawed!"), HttpStatus.OK);
  }
}
