package com.nhnacademy.mini_dooray.task_api.domain.tasks.repository;

import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
}
