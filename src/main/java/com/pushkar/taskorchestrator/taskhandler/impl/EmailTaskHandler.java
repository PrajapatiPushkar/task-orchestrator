package com.pushkar.taskorchestrator.taskhandler.impl;

import com.pushkar.taskorchestrator.entity.Task;
import com.pushkar.taskorchestrator.taskhandler.TaskHandler;
import org.springframework.stereotype.Component;

@Component
public class EmailTaskHandler implements TaskHandler {

    @Override
    public String handle(Task task) throws Exception {
        // simulate email sending
        Thread.sleep(3000);

        return "Email task completed successfully. Payload: " + task.getPayload();
    }
}