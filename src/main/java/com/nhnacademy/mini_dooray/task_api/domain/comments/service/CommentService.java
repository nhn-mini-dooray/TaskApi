package com.nhnacademy.mini_dooray.task_api.domain.comments.service;


import com.nhnacademy.mini_dooray.task_api.domain.comments.entity.Comment;
import com.nhnacademy.mini_dooray.task_api.domain.comments.model.request.CommentRequestDto;
import com.nhnacademy.mini_dooray.task_api.domain.comments.model.response.CommentResponseDto;
import com.nhnacademy.mini_dooray.task_api.domain.comments.repository.CommentRepository;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.repository.TaskRepository;
import com.nhnacademy.mini_dooray.task_api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;


    @Transactional(readOnly = true)
    public CommentResponseDto select(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);

        return new CommentResponseDto(comment.getAccountId(),comment.getTask().getTaskId(),
                comment.getCommentContent());
    }

    public void create(@NotNull CommentRequestDto requestDto){
        Task task = taskRepository.findById(requestDto.getTaskId()).orElseThrow(NotFoundException::new);

        Comment comment = CommentRequestDto.toEntity(requestDto,task);
        commentRepository.save(comment);
    }

    public void update(Long id , @NotNull CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);
        comment.updateComment(requestDto.getCommentContent());
        commentRepository.save(comment);
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }

}
