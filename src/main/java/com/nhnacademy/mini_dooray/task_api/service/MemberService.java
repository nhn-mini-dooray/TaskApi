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

    public MemberDTO createMember (MemberDTO memberDTO) {

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

    public List<ProjectDTO> getProjectForMember(Long accountId) {
        List<Member> members = memberRepository.findByPkAccountId(accountId);

        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Member member: members) {
            Project project = member.getPk().getProject();
            ProjectDTO projectDTO = new ProjectDTO(
                    project.getProjectName(),
                    project.getAccountId(),
                    project.getProjectStatusId().getProjectStatusId()
            );
            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }
}
