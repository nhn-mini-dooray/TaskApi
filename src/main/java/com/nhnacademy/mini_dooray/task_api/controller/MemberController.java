package com.nhnacademy.mini_dooray.task_api.controller;

import com.nhnacademy.mini_dooray.task_api.dto.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.dto.ProjectDTO;
import com.nhnacademy.mini_dooray.task_api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    /**
     * 멤버 아이디를 통해 프로젝트 조회
     *
     * @param accountId
     * @return
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsForMember(@PathVariable Long accountId) {
        List<ProjectDTO> getProjectsForMember = memberService.getProjectForMember(accountId);
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