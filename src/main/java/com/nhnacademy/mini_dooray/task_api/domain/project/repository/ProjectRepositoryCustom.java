package com.nhnacademy.mini_dooray.task_api.domain.project.repository;

import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectResponseDTO;

import java.util.List;

public interface ProjectRepositoryCustom {
    List<ProjectResponseDTO> getProjectForMember(Long accountId);
}