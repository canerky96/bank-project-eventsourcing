package com.springbank.user.oauth20.repository;

import com.kaya.user.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
  @Query("{'account.username': ?0}")
  Optional<User> findByUsername(String username);
}
