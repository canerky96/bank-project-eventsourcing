package com.kaya.user.query.api.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUsersQuery {
  private String filter;
}
