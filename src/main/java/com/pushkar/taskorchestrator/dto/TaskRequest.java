package com.pushkar.taskorchestrator.dto;

import com.pushkar.taskorchestrator.entity.TaskType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Task creation request")
public class TaskRequest {

    @NotBlank(message = "Task name is required")
    @Schema(example = "Generate Monthly Report")
    private String taskName;

    @NotNull(message = "Task type is required")
    @Schema(example = "REPORT")
    private TaskType taskType;

    @Schema(example = "monthly-report")
    private String payload;
}