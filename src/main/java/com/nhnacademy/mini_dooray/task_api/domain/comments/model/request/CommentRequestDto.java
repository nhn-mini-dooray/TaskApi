package com.nhnacademy.mini_dooray.task_api.domain.comments.model.request;


import com.nhnacademy.mini_dooray.task_api.domain.comments.entity.Comment;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CommentRequestDto {
    private Long taskId;
    private Long accountId;
    private String commentContent;

    public static Comment toEntity(CommentRequestDto requestDto, Task task) {
        return new Comment(task, requestDto.accountId, requestDto.getCommentContent());
    }
}
