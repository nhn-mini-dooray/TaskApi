package com.nhnacademy.mini_dooray.task_api.dto;

import com.nhnacademy.mini_dooray.task_api.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.entity.ProjectStatusName;
import lombok.Data;

@Data
public class ProjectDTO {
    private Long accountId;
    private Integer projectStatusId;
}
