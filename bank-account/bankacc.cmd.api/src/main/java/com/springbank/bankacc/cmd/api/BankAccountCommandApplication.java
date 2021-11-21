package com.springbank.bankacc.cmd.api;

import com.springbank.bankacc.core.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(AxonConfig.class)
public class BankAccountCommandApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankAccountCommandApplication.class, args);
  }
}
