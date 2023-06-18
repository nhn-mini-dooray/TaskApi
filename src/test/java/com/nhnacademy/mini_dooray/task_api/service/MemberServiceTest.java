package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.domain.member.entity.Member;
import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.member.service.MemberService;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectResponseDTO;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatusName;
import com.nhnacademy.mini_dooray.task_api.domain.role.entity.Role;
import com.nhnacademy.mini_dooray.task_api.domain.member.entity.QMember;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.QProject;
import com.nhnacademy.mini_dooray.task_api.domain.member.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.domain.role.repository.RoleRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        memberService = new MemberService(memberRepository, projectRepository, roleRepository, jpaQueryFactory);
    }

    @Test
    void testCreateMember() {

        MemberDTO memberDTO = new MemberDTO(1L, 1L, 1);

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(ArgumentMatchers.any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));

        Role role = new Role(1, "Admin");
        given(roleRepository.findById(1)).willReturn(Optional.of(role));

        Member member = new Member(new Member.Pk(1L, project), role);
        given(memberRepository.save(any(Member.class))).willReturn(member);

        MemberDTO createdMember = memberService.createMember(memberDTO);

        assertThat(createdMember.getProjectId()).isEqualTo(project.getProjectId());
        assertThat(createdMember.getAccountId()).isEqualTo(1L);
        assertThat(createdMember.getRoleId()).isEqualTo(1);
    }

    @Test
    void testGetProjectForMember() {
        QMember member = QMember.member;
        QProject project = QProject.project;

        Long accountId = 1L;
        ProjectDTO project1 = new ProjectDTO("Project 1", accountId, 1);
        ProjectDTO project2 = new ProjectDTO("Project 2", accountId, 2);
        List<ProjectDTO> expectedProjects = Arrays.asList(project1, project2);

        JPAQuery<ProjectResponseDTO> jpaQuery = Mockito.mock(JPAQuery.class);
        when(jpaQueryFactory.select(any(ConstructorExpression.class))).thenReturn(jpaQuery);
        when(jpaQuery.from(member)).thenReturn(jpaQuery);
        when(jpaQuery.join(member.pk.project, project)).thenReturn(jpaQuery);
        when(jpaQuery.where(member.pk.accountId.eq(accountId))).thenReturn(jpaQuery);
        when(jpaQuery.fetch()).thenReturn(expectedProjects);

        List<ProjectResponseDTO> projects = memberService.getProjectForMember(accountId);

        assertEquals(expectedProjects, projects);
    }

    @Test
    void testDeleteMember() {

        Long accountId = 1L;
        Long projectId = 1L;

        Role role = new Role(1, "Admin");
        given(roleRepository.findById(1)).willReturn(Optional.of(role));

        Member.Pk pk = new Member.Pk(accountId, new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE)));
        Member member = new Member(pk, role);
        when(memberRepository.findById(any(Member.Pk.class))).thenReturn(Optional.of(member));

        memberService.deleteMember(accountId, projectId);

        verify(memberRepository, times(1)).delete(member);
    }
}