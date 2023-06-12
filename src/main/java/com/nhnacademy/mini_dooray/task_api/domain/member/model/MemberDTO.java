package com.nhnacademy.mini_dooray.task_api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDTO {
    private Long accountId;
    private Long projectId;
    private Integer roleId;
}