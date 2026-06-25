package com.pushkar.taskorchestrator.taskhandler;

import com.pushkar.taskorchestrator.entity.TaskType;
import com.pushkar.taskorchestrator.taskhandler.impl.ApiSyncTaskHandler;
import com.pushkar.taskorchestrator.taskhandler.impl.EmailTaskHandler;
import com.pushkar.taskorchestrator.taskhandler.impl.ReportTaskHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskHandlerFactory {

    private final EmailTaskHandler emailTaskHandler;
    private final ReportTaskHandler reportTaskHandler;
    private final ApiSyncTaskHandler apiSyncTaskHandler;

    public TaskHandler getHandler(TaskType taskType) {
        return switch (taskType) {
            case EMAIL -> emailTaskHandler;
            case REPORT -> reportTaskHandler;
            case API_SYNC -> apiSyncTaskHandler;
        };
    }
}