package com.nhnacademy.mini_dooray.task_api.domain.member.repository;

import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberDTO> getMembersForProject(Long projectId);
}