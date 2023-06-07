package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.dto.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.dto.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.dto.ProjectStatusModifyDTO;
import com.nhnacademy.mini_dooray.task_api.entity.Member;
import com.nhnacademy.mini_dooray.task_api.entity.Project;
import com.nhnacademy.mini_dooray.task_api.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 프로젝트 이름 변경
     *
     * @param projectId
     * @param newProjectName
     * @return
     */
    public ProjectDTO updateProjectName(Long projectId, String newProjectName) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 projectId 없음"));

        project.setProjectName(newProjectName);
        Project updatedProject = projectRepository.save(project);

        return new ProjectDTO(updatedProject.getProjectName(),
                updatedProject.getAccountId(),
                updatedProject.getProjectStatusId().getProjectStatusId());
    }

    /**
     * 프로젝트 상태 변경
     *
     * @param projectId
     * @param projectStatusModifyDTO
     * @return
     */
    public ProjectDTO modifyProjectStatus(Long projectId, ProjectStatusModifyDTO projectStatusModifyDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        ProjectStatus newProjectStatus = projectStatusRepository.findById(projectStatusModifyDTO.getProjectStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid project status ID"));

        project.setProjectStatusId(newProjectStatus);

        Project savedProject = projectRepository.save(project);

        return new ProjectDTO(
                savedProject.getProjectName(),
                savedProject.getAccountId(),
                savedProject.getProjectStatusId().getProjectStatusId()
        );
    }

    /**
     * 프로젝트 아이디를 통해 멤버 조회
     * @param projectId
     * @return
     */
    public List<MemberDTO> getMembersForProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 projectId 없음"));

        List<Member> members = project.getMembers();

        List<MemberDTO> memberDTOs = new ArrayList<>();
        for (Member member : members) {
            MemberDTO memberDTO = new MemberDTO(
                    member.getPk().getAccountId(),
                    member.getPk().getProject().getProjectId(),
                    member.getRole().getRoleId()
            );
            memberDTOs.add(memberDTO);
        }
        return memberDTOs;
    }
}
