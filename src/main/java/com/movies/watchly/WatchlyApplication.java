package com.movies.watchly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WatchlyApplication {
	public static void main(String[] args) {
		SpringApplication.run(WatchlyApplication.class, args);
	}

}
