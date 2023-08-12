package com.example.todoandroidapp.service;

import com.example.todoandroidapp.model.Project;

import java.util.List;
import java.util.stream.Collectors;

public class TodoProjectImpl implements Service<Project>{

    private List<Project> projectList;

    @Override
    public void add(final Project project) {
        projectList.add(project);
    }

    @Override
    public void remove(final long id) {
        projectList = projectList.stream().filter(project -> project.getId() != id).collect(Collectors.toList());
    }

    @Override
    public List<Project> getAll() {
        return projectList;
    }
}
