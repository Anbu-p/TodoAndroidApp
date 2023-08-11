package com.example.todoandroidapp.controller;

import com.example.todoandroidapp.model.Project;
import com.example.todoandroidapp.service.Service;
import com.example.todoandroidapp.service.TodoProjectImpl;

import java.util.List;

public class ProjectController {

    private static final Service<Project> PROJECT_SERVICE = new TodoProjectImpl();

    public void add(final Project project){
        PROJECT_SERVICE.add(project);
    }

    public void remove(final long id) {
        PROJECT_SERVICE.remove(id);
    }

    public List<Project> getAll() {
        return PROJECT_SERVICE.getAll();
    }
}
