package com.nhnacademy.mini_dooray.task_api.domain.project.model;

import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatusName;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectResponseDTO {
    private Long projectId;
    private String projectName;
    private ProjectStatusName projectStatusName;
}
