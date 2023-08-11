package com.example.todoandroidapp.service;

import java.util.List;

public interface Service<T> {

    void add(final T type);

    void remove(final long id);

     List<T> getAll();
}
