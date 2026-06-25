package com.pushkar.taskorchestrator.dto;

import com.pushkar.taskorchestrator.entity.TaskType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Task name is required")
    private String taskName;

    @NotNull(message = "Task type is required")
    private TaskType taskType;

    private String payload;
}