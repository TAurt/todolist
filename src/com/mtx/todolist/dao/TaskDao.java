package com.mtx.todolist.dao;

import com.mtx.todolist.entity.Task;

import java.util.List;
import java.util.Optional;

public class TaskDao implements Dao<Long, Task>{
    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Task save(Task entity) {
        return null;
    }

    @Override
    public void update(Task entity) {
    }
}
