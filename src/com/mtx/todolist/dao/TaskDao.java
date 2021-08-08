package com.mtx.todolist.dao;

import com.mtx.todolist.entity.Priority;
import com.mtx.todolist.entity.Status;
import com.mtx.todolist.entity.Task;
import com.mtx.todolist.util.ConnectionPool;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TaskDao implements Dao<Long, Task> {

    private final static TaskDao INSTANCE = new TaskDao();

    private static final String FIND_ALL_BY_USER_ID_SQL = """
            SELECT id, user_id, title, created_date, scheduled_date, completed_date, status, priority, description
            FROM task
            WHERE user_id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO task (user_id, title, created_date, scheduled_date, completed_date, status, priority, description)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

    @SneakyThrows
    public List<Task> findAllByUserId(Integer userId) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BY_USER_ID_SQL)) {
            preparedStatement.setObject(1, userId);
            var resultSet = preparedStatement.executeQuery();

            List<Task> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(build(resultSet));
            }
            return result;
        }
    }

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

    @SneakyThrows
    @Override
    public Task save(Task task) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, task.getUserId());
            preparedStatement.setObject(2, task.getTitle());
            preparedStatement.setObject(3, task.getCreatedDate());
            preparedStatement.setObject(4, task.getScheduledDate());
            preparedStatement.setObject(5, task.getCompletedDate());
            preparedStatement.setObject(6, task.getStatus().name());
            preparedStatement.setObject(7, task.getPriority().name());
            preparedStatement.setObject(8, task.getDescription());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            task.setId(generatedKeys.getObject("id", Long.class));

            return task;
        }
    }

    @Override
    public void update(Task entity) {
    }

    public static TaskDao getInstance() {
        return INSTANCE;
    }

    private Task build(ResultSet resultSet) throws SQLException {
        return Task.builder()
                .id(resultSet.getObject("id", Long.class))
                .userId(resultSet.getObject("user_id", Integer.class))
                .title(resultSet.getObject("title", String.class))
                .createdDate(resultSet.getObject("created_date", Date.class).toLocalDate())
                .scheduledDate(resultSet.getObject("scheduled_date", Date.class).toLocalDate())
                .completedDate(resultSet.getObject("completed_date") != null
                        ? resultSet.getObject("completed_date", Date.class).toLocalDate()
                        : null)
                .status(Status.find(resultSet.getObject("status", String.class)).orElse(null))
                .priority(Priority.find(resultSet.getObject("priority", String.class)).orElse(null))
                .description(resultSet.getObject("description", String.class))
                .build();
    }
}
