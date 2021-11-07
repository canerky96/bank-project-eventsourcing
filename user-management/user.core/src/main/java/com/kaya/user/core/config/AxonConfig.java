package com.kaya.user.core.config;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AxonConfig {

  @Value("${spring.data.mongodb.host:127.0.01}")
  private String mongoHost;

  @Value("${spring.data.mongodb.port:27017}")
  private int mongoPort;

  @Value("${spring.data.mongodb.database:user}")
  private String mongoDatabase;

  @Bean
  public MongoClient mongo() {
    var mongoFactory = new MongoFactory();
    var mongoSettingsFactory = new MongoSettingsFactory();
    mongoSettingsFactory.setMongoAddresses(List.of(new ServerAddress(mongoHost, mongoPort)));
    mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());
    return mongoFactory.createMongo();
  }

  @Bean
  public MongoTemplate axonMongoTemplate() {
    return DefaultMongoTemplate.builder().mongoDatabase(mongo(), mongoDatabase).build();
  }

  @Bean
  public TokenStore tokenStore(Serializer serializer) {
    return MongoTokenStore.builder()
        .mongoTemplate(axonMongoTemplate())
        .serializer(serializer)
        .build();
  }

}
