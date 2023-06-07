package com.nhnacademy.mini_dooray.task_api.project_member.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDTO {
    private String projectName;
    private Long accountId;
    private Integer projectStatusId;
}