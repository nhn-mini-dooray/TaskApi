package com.nhnacademy.mini_dooray.task_api.domain.comments.repository;

import com.nhnacademy.mini_dooray.task_api.domain.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
