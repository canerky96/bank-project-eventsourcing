package com.kaya.user.cmd.api.controller;

import com.kaya.user.cmd.api.command.RemoveUserCommand;
import com.kaya.user.cmd.api.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/remove-user")
@RequiredArgsConstructor
public class RemoveUserController {

  private final CommandGateway commandGateway;

  @DeleteMapping("{id}")
  public ResponseEntity<BaseResponse> removeUser(@PathVariable("id") String id) {

    try {
      commandGateway.send(new RemoveUserCommand(id));
      return new ResponseEntity<>(
          new BaseResponse("User successfully removed!"), HttpStatus.CREATED);
    } catch (Exception e) {
      var safeErrorMessage = "Error while processing remove user request with id - " + id;
      return new ResponseEntity<>(
          new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
