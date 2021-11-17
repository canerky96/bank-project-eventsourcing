package com.kaya.user.query.api.handler;

import com.kaya.user.query.api.dto.UserLookupResponse;
import com.kaya.user.query.api.query.FindAllUsersQuery;
import com.kaya.user.query.api.query.FindUserByIdQuery;
import com.kaya.user.query.api.query.SearchUsersQuery;
import com.kaya.user.query.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {

  private final UserRepository userRepository;

  @QueryHandler
  @Override
  public UserLookupResponse getUserById(FindUserByIdQuery query) {
    var user = userRepository.findById(query.getId());
    return user.isPresent() ? new UserLookupResponse(user.get()) : null;
  }

  @QueryHandler
  @Override
  public UserLookupResponse searchUsers(SearchUsersQuery query) {
    var users = new ArrayList<>(userRepository.findByFilterRegex(query.getFilter()));
    return new UserLookupResponse(users);
  }

  @QueryHandler
  @Override
  public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
    var users = new ArrayList<>(userRepository.findAll());
    return new UserLookupResponse(users);
  }
}
