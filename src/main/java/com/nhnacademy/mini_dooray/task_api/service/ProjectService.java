package com.nhnacademy.mini_dooray.task_api.project_member.service;

import com.nhnacademy.mini_dooray.task_api.dto.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.entity.Project;
import com.nhnacademy.mini_dooray.task_api.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;

    /**
     * 프로젝트 생성
     *
     * @param projectDTO
     * @return
     */
    public ProjectDTO createProject(ProjectDTO projectDTO) {

        ProjectStatus projectStatusId = projectStatusRepository.findById(projectDTO.getProjectStatusId()).orElse(null);

        Project project = new Project(projectDTO.getProjectName(), projectDTO.getAccountId(), projectStatusId);

        Project savedProject = projectRepository.save(project);

        return new ProjectDTO(savedProject.getProjectName(), savedProject.getAccountId(),
                savedProject.getProjectStatusId().getProjectStatusId());
    }
}
