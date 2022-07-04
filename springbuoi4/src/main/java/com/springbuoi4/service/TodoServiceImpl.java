package com.springbuoi4.service;


import com.springbuoi4.apierror.NotFoundException;
import com.springbuoi4.entity.Todo;
import com.springbuoi4.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo findById(Long id) {
        List<Todo> todos = todoRepository.findAll();
        Todo todo = null;
        if(todos == null)
            throw new NotFoundException(Todo.class , id);
        for(Todo td : todos){
            if(td.getId() == id)
                todo = td;
        }
        if(todo == null)
            throw new NotFoundException(Todo.class , id);
        return todo;
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> findAllTodoContainTitle(String title) {
        return todoRepository.findByTitleContains(title);
    }
}
