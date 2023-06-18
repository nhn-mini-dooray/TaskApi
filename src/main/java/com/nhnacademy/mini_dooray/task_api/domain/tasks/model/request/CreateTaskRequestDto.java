package com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request;

import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.MileStone;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateTaskRequestDto {
    private Long projectId;
    private Long mileStoneId;
    private String name;
    private String content;

    public static Task toEntity(CreateTaskRequestDto requestDto, Project project, MileStone mileStone){
        return new Task(project,mileStone, requestDto.getName(), requestDto.getContent());
    }
}
