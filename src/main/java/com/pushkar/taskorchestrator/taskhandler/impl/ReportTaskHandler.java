package com.pushkar.taskorchestrator.taskhandler.impl;

import com.pushkar.taskorchestrator.entity.Task;
import com.pushkar.taskorchestrator.taskhandler.TaskHandler;
import org.springframework.stereotype.Component;

@Component
public class ReportTaskHandler implements TaskHandler {

    @Override
    public String handle(Task task) throws Exception {

        if (task.getPayload() != null && task.getPayload().toUpperCase().contains("FAIL")) {
            throw new RuntimeException("Report generation failed intentionally for testing.");
        }

        Thread.sleep(5000);
        return "Report generated successfully. Payload: " + task.getPayload();
    }
}