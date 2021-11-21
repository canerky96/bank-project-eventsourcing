package com.kaya.user.cmd.api.controller;

import com.kaya.user.cmd.api.command.UpdateUserCommand;
import com.kaya.user.cmd.api.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1/update-user")
@RequiredArgsConstructor
public class UpdateUserController {

  private final CommandGateway commandGateway;

  @PutMapping("{id}")
  @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
  public ResponseEntity<BaseResponse> updateUser(
      @PathVariable("id") String id, @Validated @RequestBody UpdateUserCommand command) {
    try {
      command.setId(id);
      commandGateway.send(command);
      return new ResponseEntity<>(new BaseResponse("User successfully updated"), HttpStatus.OK);
    } catch (Exception e) {
      var safeErrorMessage =
          "Error while processing update user request with id - " + command.getId();
      return new ResponseEntity<>(
          new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
