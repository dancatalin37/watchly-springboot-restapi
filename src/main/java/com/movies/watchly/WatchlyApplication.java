package com.movies.watchly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication
public class WatchlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchlyApplication.class, args);
	}

}
