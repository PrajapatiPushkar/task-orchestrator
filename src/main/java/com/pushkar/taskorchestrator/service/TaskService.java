package com.pushkar.taskorchestrator.service;

import com.pushkar.taskorchestrator.dto.TaskRequest;
import com.pushkar.taskorchestrator.entity.Task;
import com.pushkar.taskorchestrator.entity.TaskStatus;
import com.pushkar.taskorchestrator.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ExecutorService executorService;

    public Task createTask(TaskRequest request) {
        Task task = Task.builder()
                .taskName(request.getTaskName())
                .taskType(request.getTaskType())
                .payload(request.getPayload())
                .status(TaskStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .build();

        Task savedTask = taskRepository.save(task);

        // Run asynchronously using virtual thread
        executorService.submit(() -> executeTask(savedTask.getId()));

        return savedTask;
    }

    public void executeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));

        try {
            task.setStatus(TaskStatus.RUNNING);
            task.setStartedAt(LocalDateTime.now());
            taskRepository.save(task);

            // Simulate actual work
            Thread.sleep(5000);

            String output = "Task executed successfully for taskType: " + task.getTaskType();

            task.setResult(output);
            task.setStatus(TaskStatus.SUCCESS);
            task.setCompletedAt(LocalDateTime.now());
            taskRepository.save(task);

        } catch (Exception e) {
            task.setStatus(TaskStatus.FAILED);
            task.setErrorMessage(e.getMessage());
            task.setCompletedAt(LocalDateTime.now());
            taskRepository.save(task);
        }
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}