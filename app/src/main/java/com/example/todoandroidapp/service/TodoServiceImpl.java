package com.example.todoandroidapp.service;


import com.example.todoandroidapp.model.Todo;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements Service<Todo> {

    private List<Todo> todoList;

    @Override
    public void add(final Todo todo) {
        todoList.add(todo);
    }

    @Override
    public void remove(long id) {
        todoList = todoList.stream().filter(todo -> todo.getId() != (id)).collect(Collectors.toList());
    }

    @Override
    public List<Todo> getAll() {
        //if (parentId == null) {
            return todoList;
       // }
       // return  todoList.stream().filter(todo -> todo.getParentId().equals(parentId)).collect(Collectors.toList());
    }
}

