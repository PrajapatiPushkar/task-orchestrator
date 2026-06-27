package com.pushkar.taskorchestrator.controller;

import com.pushkar.taskorchestrator.dto.TaskRequest;
import com.pushkar.taskorchestrator.entity.Task;
import com.pushkar.taskorchestrator.entity.TaskStatus;
import com.pushkar.taskorchestrator.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "Task APIs",
        description = "APIs for managing background tasks"
)
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task task = taskService.createTask(request);
        return ResponseEntity.ok(task);
    }

    @Operation(summary = "Get task by id")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Operation(summary = "Retry failed task")
    @PostMapping("/{id}/retry")
    public ResponseEntity<Task> retryTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.retryTask(id));
    }

    @Operation(summary = "Get tasks by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }
}