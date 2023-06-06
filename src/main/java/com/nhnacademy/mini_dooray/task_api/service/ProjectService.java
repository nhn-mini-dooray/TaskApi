package com.nhnacademy.mini_dooray.task_api.service;


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

    public ProjectDTO createProject(ProjectDTO projectDTO) {

        ProjectStatus status = projectStatusRepository.findById(projectDTO.getProjectStatusId()).orElse(null);

        Project project = new Project();
        project.setAccountId(projectDTO.getAccountId());
        project.setProjectStatusId(status);

        Project savedProject = projectRepository.save(project);

        ProjectDTO createdProjectDTO = new ProjectDTO();
        createdProjectDTO.setAccountId(savedProject.getAccountId());
        createdProjectDTO.setProjectStatusId(savedProject.getProjectStatusId().getProjectStatusId());


        return createdProjectDTO;
    }

}

