package com.nhnacademy.mini_dooray.task_api.entity;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum ProjectStatus {
    ACTIVE  ("활성"),
    INACTIVE("휴먼"),
    COMPLETE("종료");

    @Column(name = "project_status_name")
    private final String description;
}


