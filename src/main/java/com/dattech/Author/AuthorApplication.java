package com.dattech.Author;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableWebMvc
@SpringBootApplication
public class AuthorApplication {

	final static Logger logger = LoggerFactory.getLogger(AuthorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AuthorApplication.class, args);
	}

}
