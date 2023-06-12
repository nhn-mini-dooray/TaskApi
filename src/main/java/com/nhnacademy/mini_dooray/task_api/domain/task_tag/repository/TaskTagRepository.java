package com.nhnacademy.mini_dooray.account_api.domain.task_tag.repository;

import com.nhnacademy.mini_dooray.account_api.domain.tags.entity.Tag;
import com.nhnacademy.mini_dooray.account_api.domain.task_tag.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, TaskTag.Pk> {
}
