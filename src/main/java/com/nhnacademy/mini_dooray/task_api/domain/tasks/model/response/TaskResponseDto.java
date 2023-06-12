package com.nhnacademy.mini_dooray.task_api.domain.tasks.model.response;

import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TaskResponseDto {
    private Long taskId;
    private Long projectId;
    private Long mileStoneId;
    private String name;
    private String content;

    public static TaskResponseDto fromEntity(Task task){
        return new TaskResponseDto(task.getTaskId(), task.getProject().getProjectId(),
                task.getMileStone().getMileStoneId(), task.getTaskName(), task.getTaskContent());
    }


}
