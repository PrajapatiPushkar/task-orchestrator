package com.pushkar.taskorchestrator.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Task name is required")
    private String taskName;

    @NotBlank(message = "Task type is required")
    private String taskType;

    private String payload;
}