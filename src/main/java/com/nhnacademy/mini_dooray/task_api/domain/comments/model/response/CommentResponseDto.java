package com.nhnacademy.mini_dooray.task_api.domain.comments.model.response;

import lombok.*;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    Long accountId;
    Long taskId;
    String commentContent;
}
