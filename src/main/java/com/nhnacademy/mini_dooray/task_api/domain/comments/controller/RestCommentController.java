package com.nhnacademy.mini_dooray.task_api.domain.comments.controller;


import com.nhnacademy.mini_dooray.task_api.domain.comments.model.request.CommentRequestDto;
import com.nhnacademy.mini_dooray.task_api.domain.comments.model.response.CommentResponseDto;
import com.nhnacademy.mini_dooray.task_api.domain.comments.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class RestCommentController {

    private final CommentService commentService;

    @GetMapping("{id}/select")
    public ResponseEntity<CommentResponseDto> selectCommentHandler(@PathVariable(name = "id") Long id) {
        return ResponseEntity
                .ok(commentService.select(id));

    }

    @PostMapping("/create")
    public ResponseEntity<Void> createCommentHandler(@Valid @RequestBody CommentRequestDto requestDto) {
        commentService.create(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Void> updateCommentHandler(@PathVariable(name = "id") Long id,
                                                     @Valid @RequestBody CommentRequestDto requestDto){
        commentService.update(id,requestDto);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Void> deleteCommentHandler(@PathVariable(name = "id") Long id){
        commentService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
