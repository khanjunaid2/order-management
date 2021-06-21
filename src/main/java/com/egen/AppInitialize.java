package com.egen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@SpringBootApplication
public class AppInitialize {
/**
 * implement the following methods
 */

public static void main(String[] args) {
	SpringApplication app= new SpringApplication(AppInitialize.class);
	app.run();
}
}
