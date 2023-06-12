package com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateTaskRequestDto {
    private String name;
    private String content;

}
