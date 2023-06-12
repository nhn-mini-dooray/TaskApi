package com.nhnacademy.mini_dooray.task_api.domain.project.service;

import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.domain.member.entity.QMember;
import com.nhnacademy.mini_dooray.task_api.domain.role.entity.QRole;

import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.repository.ProjectStatusRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;
    private final JPAQueryFactory jpaQueryFactory;

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
     * @param newProejctStatusId
     * @return
     */
    public ProjectDTO modifyProjectStatus(Long projectId, Integer newProejctStatusId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        ProjectStatus newProjectStatus = projectStatusRepository.findById(newProejctStatusId)
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
        QMember member = QMember.member;
        QRole role = QRole.role;

        return jpaQueryFactory
                .select(Projections.constructor(MemberDTO.class,
                        member.pk.accountId,
                        member.pk.project.projectId,
                        role.roleId))
                .from(member)
                .where(member.pk.project.projectId.eq(projectId))
                .fetch();
    }
}
