package com.mtx.todolist.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateTaskDto {
    String userId;
    String title;
    String createdDate;
    String scheduledDate;
    String completedDate;
    String status;
    String priority;
    String description;
}
