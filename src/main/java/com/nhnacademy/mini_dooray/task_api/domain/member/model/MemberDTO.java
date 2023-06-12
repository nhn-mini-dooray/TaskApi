package com.nhnacademy.mini_dooray.task_api.domain.member.model;

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