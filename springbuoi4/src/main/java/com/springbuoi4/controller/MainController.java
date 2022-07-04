package com.springbuoi4.controller;


import com.springbuoi4.apierror.NotFoundException;
import com.springbuoi4.entity.Todo;
import com.springbuoi4.repository.TodoRepository;
import com.springbuoi4.service.TodoServiceImpl;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.*;
@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    private TodoServiceImpl todoService;

    @Autowired
    private TodoRepository todoRepository;
    @GetMapping("/todos")
    public List<Todo> getTodosContainTitle(@RequestParam(value = "title" , required = false) String title) {

        if(title != null) {
            return todoService.findAllTodoContainTitle(title);
        }
        else {
            return todoService.findAll();
        }
    }

    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable(value = "id") Long id)  throws NotFoundException {
        Todo todo = todoService.findById(id);
        return todo;
    }

    @PostMapping("/todos")
    public ResponseEntity addTodo(@Valid @RequestBody Todo todo) {
        todoService.save(todo);
        return ResponseEntity.ok().body(todo);
    }

    @PutMapping("/todos/{id}")
    public Todo editTodo(@PathVariable Long id ,@Valid @RequestBody Todo todo){
        Todo currentTodo = todoService.findById(id);
        currentTodo.setTitle(todo.getTitle());
        todoService.save(currentTodo);
        return currentTodo;
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id){
        todoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
