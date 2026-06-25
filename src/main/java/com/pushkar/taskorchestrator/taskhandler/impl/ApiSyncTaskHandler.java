package com.pushkar.taskorchestrator.taskhandler.impl;

import com.pushkar.taskorchestrator.entity.Task;
import com.pushkar.taskorchestrator.taskhandler.TaskHandler;
import org.springframework.stereotype.Component;

@Component
public class ApiSyncTaskHandler implements TaskHandler {

    @Override
    public String handle(Task task) throws Exception {
        // simulate external API sync
        Thread.sleep(4000);

        return "API sync completed successfully. Payload: " + task.getPayload();
    }
}