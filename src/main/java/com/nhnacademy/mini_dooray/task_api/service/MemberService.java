package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.dto.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.dto.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.entity.Member;
import com.nhnacademy.mini_dooray.task_api.entity.Project;
import com.nhnacademy.mini_dooray.task_api.entity.Role;
import com.nhnacademy.mini_dooray.task_api.repository.MemberRepository;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;

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
        List<Member> members = memberRepository.findByPkAccountId(accountId);

        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Member member: members) {
            Project project = member.getPk().getProject();
            ProjectDTO projectDTO = new ProjectDTO(
                    project.getProjectName(),
                    member.getPk().getAccountId(),
                    project.getProjectStatusId().getProjectStatusId()
            );
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
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