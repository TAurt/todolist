package com.mtx.todolist.service;

import com.mtx.todolist.dao.TaskDao;
import com.mtx.todolist.dto.CreateTaskDto;
import com.mtx.todolist.entity.Task;
import com.mtx.todolist.mapper.CreateTaskMapper;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TaskService {

    private static final TaskService INSTANCE = new TaskService();

    private final TaskDao taskDao = TaskDao.getInstance();
    private final CreateTaskMapper createTaskMapper = CreateTaskMapper.getInstance();

    public Long create(CreateTaskDto createTaskDto) {
        var task = createTaskMapper.mapFrom(createTaskDto);
        var saveTask = taskDao.save(task);
        return saveTask.getId();
    }

    public List<Task> getAllByUserId(Integer userId) {
        return taskDao.findAllByUserId(userId);
    }

    public boolean delete(Long id) {
        return taskDao.delete(id);
    }

    public Optional<Task> get(Long id) {
        return taskDao.findById(id);
    }

    public void update(Task task) {
        taskDao.update(task);
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }
}
