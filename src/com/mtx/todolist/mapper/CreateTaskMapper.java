package com.mtx.todolist.mapper;

import com.mtx.todolist.dto.CreateTaskDto;
import com.mtx.todolist.entity.Priority;
import com.mtx.todolist.entity.Status;
import com.mtx.todolist.entity.Task;
import com.mtx.todolist.util.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateTaskMapper implements Mapper<CreateTaskDto, Task>{

    private static final CreateTaskMapper INSTANCE = new CreateTaskMapper();

    @Override
    public Task mapFrom(CreateTaskDto createTaskDto) {
        return Task.builder()
                .userId(Integer.valueOf(createTaskDto.getUserId()))
                .title(createTaskDto.getTitle())
                .createdDate(LocalDateFormatter.format(createTaskDto.getCreatedDate()))
                .scheduledDate(LocalDateFormatter.format(createTaskDto.getScheduledDate()))
                .completedDate(null)
                .status(Status.valueOf(createTaskDto.getStatus()))
                .priority(Priority.valueOf(createTaskDto.getPriority()))
                .description(createTaskDto.getDescription())
                .build();
    }

    public static CreateTaskMapper getInstance() {
        return INSTANCE;
    }
}
