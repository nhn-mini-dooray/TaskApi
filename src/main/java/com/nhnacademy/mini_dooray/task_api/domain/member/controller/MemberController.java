package com.nhnacademy.mini_dooray.task_api.domain.member.controller;

import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.domain.member.service.MemberService;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/project/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 멤버 추가
     *
     * @param memberDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.createMember(memberDTO);
        URI location = UriComponentsBuilder.fromPath("/members/{id}")
                .buildAndExpand(createdMember.getAccountId())
                .toUri();
        return ResponseEntity.created(location).body(createdMember);
    }

    /**
     * 멤버 아이디를 통해 프로젝트 조회
     *
     * @param accountId
     * @return
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsForMember(@PathVariable Long accountId) {
        List<ProjectResponseDTO> getProjectsForMember = memberService.getProjectForMember(accountId);
        return ResponseEntity.ok(getProjectsForMember);
    }

    /**
     * 멤버 삭제
     *
     * @param accountId
     * @param proejctId
     * @return
     */
    @DeleteMapping("/{accountId}/{proejctId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long accountId, @PathVariable Long proejctId) {
        memberService.deleteMember(accountId, proejctId);
        return ResponseEntity.noContent().build();
    }
}