package com.springbank.user.oauth20.service;

import com.springbank.user.oauth20.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByUsername(username);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Incorrect Username / Password supplied!");
    }

    var account = user.get().getAccount();

    return org.springframework.security.core.userdetails.User.withUsername(username)
        .password(account.getPassword())
        .authorities(account.getRoles())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }
}
