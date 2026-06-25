package com.pushkar.taskorchestrator.taskhandler;

import com.pushkar.taskorchestrator.entity.Task;

public interface TaskHandler {
    String handle(Task task) throws Exception;
}