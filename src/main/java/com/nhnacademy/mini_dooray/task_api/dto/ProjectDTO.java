package com.nhnacademy.mini_dooray.task_api.project.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class ProjectDTO {

    private String projectName;
    private String accountId;
    private Long projectStatusId;
}
