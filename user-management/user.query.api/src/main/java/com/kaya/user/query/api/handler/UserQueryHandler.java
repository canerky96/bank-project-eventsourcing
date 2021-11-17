package com.kaya.user.query.api.handler;

import com.kaya.user.query.api.dto.UserLookupResponse;
import com.kaya.user.query.api.query.FindAllUsersQuery;
import com.kaya.user.query.api.query.FindUserByIdQuery;
import com.kaya.user.query.api.query.SearchUsersQuery;

public interface UserQueryHandler {
  UserLookupResponse getUserById(FindUserByIdQuery findUserByIdQuery);

  UserLookupResponse searchUsers(SearchUsersQuery searchUsersQuery);

  UserLookupResponse getAllUsers(FindAllUsersQuery findAllUsersQuery);
}
