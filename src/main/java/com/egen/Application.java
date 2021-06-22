package com.egen;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@SpringBootApplication
public class Application  {
	//TODO: override addCorsMappings Method to allow cross origin request to API
    public static void main(String[] args) {
        SpringApplication  app = new SpringApplication(Application.class);
        app.run();
    }
}
