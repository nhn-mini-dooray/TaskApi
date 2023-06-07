package com.nhnacademy.mini_dooray.task_api.controller;

import com.nhnacademy.mini_dooray.task_api.dto.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.dto.ProjectNameModifyDTO;
import com.nhnacademy.mini_dooray.task_api.dto.ProjectStatusModifyDTO;
import com.nhnacademy.mini_dooray.task_api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * 프로젝트 생성
     *
     * @param projectDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    /**
     * 프로젝트 이름 변경
     *
     * @param projectId
     * @param projectModifyDTO
     * @return
     */
    @PutMapping("/{projectId}/name")
    public ResponseEntity<ProjectDTO> updateProjectName(
            @PathVariable("projectId") Long projectId,
            @RequestBody ProjectNameModifyDTO projectModifyDTO) {
        ProjectDTO updatedProject = projectService.updateProjectName(projectId, projectModifyDTO.getProjectName());
        return ResponseEntity.ok(updatedProject);
    }

    /**
     * 프로젝트 상태 변경
     *
     * @param projectId
     * @param projectStatusModifyDTO
     * @return
     */
    @PutMapping("/{projectId}/status")
    public ResponseEntity<ProjectDTO> modifyProjectStatus(
            @PathVariable Long projectId,
            @RequestBody ProjectStatusModifyDTO projectStatusModifyDTO) {
        ProjectDTO modifiedProject = projectService.modifyProjectStatus(projectId, projectStatusModifyDTO);
        return ResponseEntity.ok(modifiedProject);
    }
}