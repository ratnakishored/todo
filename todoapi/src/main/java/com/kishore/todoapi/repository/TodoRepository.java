package com.kishore.todoapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kishore.todoapi.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String>{

}
