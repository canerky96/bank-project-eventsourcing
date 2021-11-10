package com.kaya.user.query.api;

import com.kaya.user.core.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApplication {

  public static void main(String[] args) {
    SpringApplication.run(UserQueryApplication.class, args);
  }
}
