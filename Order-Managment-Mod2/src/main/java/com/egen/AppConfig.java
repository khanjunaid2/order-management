package com.egen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppConfig  {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(AppConfig.class);
    app.run();
  }
}