package com.pushkar.taskorchestrator.service;

import com.pushkar.taskorchestrator.dto.TaskRequest;
import com.pushkar.taskorchestrator.entity.Task;
import com.pushkar.taskorchestrator.entity.TaskStatus;
import com.pushkar.taskorchestrator.exception.ResourceNotFoundException;
import com.pushkar.taskorchestrator.repository.TaskRepository;
import com.pushkar.taskorchestrator.taskhandler.TaskHandler;
import com.pushkar.taskorchestrator.taskhandler.TaskHandlerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private static final int MAX_RETRY_COUNT = 3;

    private final TaskRepository taskRepository;
    private final ExecutorService executorService;
    private final TaskHandlerFactory taskHandlerFactory;

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

        executorService.submit(() -> executeTask(savedTask.getId()));

        return savedTask;
    }

    public void executeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + taskId));

        try {
            task.setStatus(TaskStatus.RUNNING);
            task.setStartedAt(LocalDateTime.now());
            task.setErrorMessage(null); // clear previous error before retry
            taskRepository.save(task);

            TaskHandler handler = taskHandlerFactory.getHandler(task.getTaskType());
            String output = handler.handle(task);

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

    public Task retryTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + taskId));

        if (task.getStatus() != TaskStatus.FAILED) {
            throw new RuntimeException("Only FAILED tasks can be retried.");
        }

        if (task.getRetryCount() >= MAX_RETRY_COUNT) {
            throw new RuntimeException("Retry limit exceeded for task id: " + taskId);
        }

        task.setRetryCount(task.getRetryCount() + 1);
        task.setStatus(TaskStatus.RETRYING);
        task.setStartedAt(null);
        task.setCompletedAt(null);
        task.setResult(null);
        task.setErrorMessage(null);

        Task updatedTask = taskRepository.save(task);

        executorService.submit(() -> executeTask(updatedTask.getId()));

        return updatedTask;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}