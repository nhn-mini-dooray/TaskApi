package com.nhnacademy.mini_dooray.task_api.domain.role.entity;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum RoleName {

    ADMIN("관리자"),
    MEMBER("구성원");
    private final String demonstration;
}
