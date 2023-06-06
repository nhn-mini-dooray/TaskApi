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

    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.createMember(memberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsForMember(@PathVariable Long accountId) {
        List<ProjectDTO> getProjectsForMember = memberService.getProjectForMember(accountId);
        return ResponseEntity.ok(getProjectsForMember);
    }
}
