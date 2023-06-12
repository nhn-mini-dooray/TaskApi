package com.nhnacademy.mini_dooray.account_api.domain.comments.model.response;


import com.nhnacademy.mini_dooray.account_api.domain.comments.entity.Comment;
import com.nhnacademy.mini_dooray.account_api.domain.comments.model.request.CommentRequestDto;
import lombok.*;

import java.lang.reflect.Constructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    Long accountId;
    Long taskId;
    String commentContent;
}
