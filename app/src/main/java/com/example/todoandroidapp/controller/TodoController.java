package com.example.todoandroidapp.controller;


import com.example.todoandroidapp.model.Todo;
import com.example.todoandroidapp.service.Service;
import com.example.todoandroidapp.service.TodoServiceImpl;

import java.util.List;

public class TodoController {

    private static final Service<Todo> PROJECT_SERVICE = new TodoServiceImpl();

    public void add(final Todo todo) {
        PROJECT_SERVICE.add(todo);
    }

    public void remove(final long id) {
        PROJECT_SERVICE.remove(id);
    }

    public List<Todo> getAll() {
        return PROJECT_SERVICE.getAll();
    }
}
