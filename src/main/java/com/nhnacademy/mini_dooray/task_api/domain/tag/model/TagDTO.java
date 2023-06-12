package com.nhnacademy.mini_dooray.task_api.domain.tag.model;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagDTO {

    private Long projectId;
    private String tagName;
}
