package com.kishore.todoapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.kishore.todoapi.model.Todo;
import com.kishore.todoapi.repository.TodoRepository;

@SpringBootApplication
public class TodoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoapiApplication.class, args);
	}
	
	@Bean
	CommandLineRunner insertData(TodoRepository todoRepository) {
		return args -> {
			todoRepository.save(new Todo("Doing Spring boot"));
			todoRepository.save(new Todo("Do Angular 2"));
		};
		
	}
}
