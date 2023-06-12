package com.nhnacademy.mini_dooray.task_api.domain.project.controller;

import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectNameModifyDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectStatusModifyDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        URI location = UriComponentsBuilder.fromPath("/{id}")
                .buildAndExpand(createdProject.getProjectName())
                .toUri();
        return ResponseEntity.created(location).body(createdProject);
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
        ProjectDTO modifiedProject = projectService.modifyProjectStatus(projectId, projectStatusModifyDTO.getProjectStatusId());
        return ResponseEntity.ok(modifiedProject);
    }

    /**
     * 프로젝트 아이디를 통해 멤버 조회
     *
     * @param projectId
     * @return
     */
    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<MemberDTO>> getMembersForProject(@PathVariable Long projectId) {
        List<MemberDTO> memberDTOs = projectService.getMembersForProject(projectId);
        return ResponseEntity.ok(memberDTOs);
    }
}