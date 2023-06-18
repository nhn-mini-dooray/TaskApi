package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.service.ProjectService;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatusName;
import com.nhnacademy.mini_dooray.task_api.domain.member.entity.QMember;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.repository.ProjectStatusRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    private ProjectService projectService;
    @MockBean
    private ProjectRepository projectRepository;
    @MockBean
    private ProjectStatusRepository projectStatusRepository;
    @MockBean
    private JPAQueryFactory jpaQueryFactory;


    @Test
    void testCreateProject() {

        ProjectDTO projectDTO = new ProjectDTO("New Project", 1L, 1);

        ProjectStatus projectStatus = new ProjectStatus(1, ProjectStatusName.ACTIVE);
        given(projectStatusRepository.findById(1)).willReturn(Optional.of(projectStatus));

        Project project = new Project("New Project", 1L, projectStatus);
        given(projectRepository.save(any(Project.class))).willReturn(project);

        ProjectDTO createdProject = projectService.createProject(projectDTO);

        assertThat(createdProject.getProjectName()).isEqualTo("New Project");
        assertThat(createdProject.getAccountId()).isEqualTo(1L);
        assertThat(createdProject.getProjectStatusId()).isEqualTo(1);
    }

    @Test
    void testUpdateProjectName() {

        String newProjectName = "updated Project";

        Project project = new Project("Old Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));


        ProjectDTO updatedProjectName = projectService.updateProjectName(1L, newProjectName);

        assertThat(updatedProjectName.getProjectName()).isEqualTo(newProjectName);
        assertThat(updatedProjectName.getAccountId()).isEqualTo(1L);
        assertThat(updatedProjectName.getProjectStatusId()).isEqualTo(project.getProjectStatusId().getProjectStatusId());
    }

    @Test
    void testModifyProjectStatus() {

        Integer newProjectStatusId = 2;

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));


        ProjectStatus projectStatus = new ProjectStatus(2, ProjectStatusName.INACTIVE);
        given(projectStatusRepository.findById(2)).willReturn(Optional.of(projectStatus));

        ProjectDTO updatedProject = projectService.modifyProjectStatus(1L, newProjectStatusId);

        assertThat(updatedProject.getProjectStatusId()).isEqualTo(newProjectStatusId);
    }

    @Test
    void testGetMembersForProject() {

        QMember member = QMember.member;

        Long projectId = 1L;
        MemberDTO member1 = new MemberDTO(1L, 1L, 1);
        MemberDTO member2 = new MemberDTO(2L, 1L, 2);
        List<MemberDTO> expectedMembers = Arrays.asList(member1, member2);

        JPAQuery<MemberDTO> jpaQuery = Mockito.mock(JPAQuery.class);
        when(jpaQueryFactory.select(any(ConstructorExpression.class))).thenReturn(jpaQuery);
        when(jpaQuery.from(member)).thenReturn(jpaQuery);
        when(jpaQuery.where(member.pk.project.projectId.eq(projectId))).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(expectedMembers);

        List<MemberDTO> members = projectService.getMembersForProject(projectId);

        assertEquals(expectedMembers.size(), members.size());
        for (int i = 0; i < expectedMembers.size(); i++) {
            assertEquals(expectedMembers.get(i), members.get(i));
        }
    }
}