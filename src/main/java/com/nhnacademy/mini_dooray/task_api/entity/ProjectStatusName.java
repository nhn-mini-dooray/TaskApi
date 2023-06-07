package com.nhnacademy.mini_dooray.task_api.entity;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
public enum ProjectStatusName {
    ACTIVE("활성"),
    INACTIVE("휴먼"),
    COMPLETE("종료");

    private final String description;
}