package com.nhnacademy.mini_dooray.task_api.domain.member.service;

import com.nhnacademy.mini_dooray.task_api.domain.member.entity.QMember;
import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.QProject;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.domain.member.entity.Member;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.role.entity.Role;
import com.nhnacademy.mini_dooray.task_api.domain.role.repository.RoleRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.nhnacademy.mini_dooray.task_api.domain.member.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;

    private final RoleRepository roleRepository;

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 멤버 추가
     *
     * @param memberDTO
     * @return
     */
    public MemberDTO createMember(MemberDTO memberDTO) {

        Project project = projectRepository.findById(memberDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("projectId Not Found"));

        Role role = roleRepository.findById(memberDTO.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("RoleId Not Found"));

        Member.Pk pk = new Member.Pk(memberDTO.getAccountId(), project);

        Member member = new Member(pk, role);

        Member savedMember = memberRepository.save(member);

        return new MemberDTO(
                savedMember.getPk().getAccountId(),
                savedMember.getPk().getProject().getProjectId(),
                savedMember.getRole().getRoleId());
    }

    /**
     * 멤버 아이디를 통해 프로젝트 조회
     *
     * @param accountId
     * @return
     */
    public List<ProjectDTO> getProjectForMember(Long accountId) {
        QMember member = QMember.member;
        QProject project = QProject.project;

        return jpaQueryFactory
                .select(Projections.constructor(ProjectDTO.class,
                        project.projectName,
                        member.pk.accountId,
                        project.projectStatusId.projectStatusId))
                .from(member)
                .join(member.pk.project, project)
                .where(member.pk.accountId.eq(accountId))
                .fetch();
    }

    /**
     * 멤버 삭제
     *
     * @param accountId
     * @param projectId
     */
    @Transactional
    public void deleteMember(Long accountId, Long projectId) {
        Member.Pk pk = new Member.Pk(accountId, projectRepository.getById(projectId));
        Member member = memberRepository.findById(pk)
                .orElseThrow(() -> new IllegalArgumentException("MemberId Not Found"));

        memberRepository.delete(member);
    }
}