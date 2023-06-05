package com.nhnacademy.mini_dooray.task_api.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum ProjectStatusName {
    ACTIVE  ("활성"),
    INACTIVE("휴먼"),
    COMPLETE("종료");

    private final String description;
}


