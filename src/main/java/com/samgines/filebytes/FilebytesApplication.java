package com.samgines.filebytes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FilebytesApplication {
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(FilebytesApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}
}
