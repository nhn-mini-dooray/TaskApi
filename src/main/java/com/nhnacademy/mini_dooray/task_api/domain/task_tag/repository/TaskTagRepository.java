package com.nhnacademy.mini_dooray.task_api.domain.task_tag.repository;

import com.nhnacademy.mini_dooray.task_api.domain.task_tag.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
}