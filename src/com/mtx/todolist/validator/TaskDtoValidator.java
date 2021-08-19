package com.mtx.todolist.validator;

import com.mtx.todolist.dto.TaskDto;
import com.mtx.todolist.util.LocalDateFormatter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class TaskDtoValidator implements Validator<TaskDto>{

    private static final TaskDtoValidator INSTANCE = new TaskDtoValidator();

    @Override
    public ValidationResult isValid(TaskDto taskDto) {
        ValidationResult validationResult = new ValidationResult();

        if (taskDto.getTitle().isBlank()) {
            validationResult.add(Error.of("title.invalid", "Title can not be empty"));
        }

        if (!LocalDateFormatter.isValid(taskDto.getScheduledDate())) {
            validationResult.add(Error.of("scheduledDate.invalid", "Scheduled date is invalid"));
        }

        if (LocalDateFormatter.isValid(taskDto.getScheduledDate()) && LocalDateFormatter.isValid(taskDto.getCreatedDate())) {
            var createdDate = LocalDateFormatter.format(taskDto.getCreatedDate());
            var scheduledDate = LocalDateFormatter.format(taskDto.getScheduledDate());
            if (createdDate.isAfter(LocalDate.now())) {
                validationResult.add(Error.of("createdDate.invalid", "Created date cannot be after today"));
            } else if (createdDate.isAfter(scheduledDate)) {
                validationResult.add(Error.of("scheduledDate.invalid", "Scheduled date cannot be before created date"));
            }
        }

        return validationResult;
    }

    public static TaskDtoValidator getInstance() {
        return INSTANCE;
    }

}
