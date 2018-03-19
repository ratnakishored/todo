package com.kishore.todoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishore.todoapi.model.Todo;
import com.kishore.todoapi.repository.TodoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		System.out.println("I m here");
		return todoRepository.findAll(new Sort(Sort.Direction.DESC, "createdDate"));
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getToDoById(@PathVariable("id") String id) {
		return todoRepository.findById(id)
				.map (todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PutMapping(value="/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") String id,
                                           @RequestBody Todo todo) {
        return todoRepository.findById(id)
                .map(todoData -> {
                    todoData.setTitle(todo.getTitle());
                    Todo updatedTodo = todoRepository.save(todoData);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }	
	
	@DeleteMapping(value="/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") String id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }	
	

}
