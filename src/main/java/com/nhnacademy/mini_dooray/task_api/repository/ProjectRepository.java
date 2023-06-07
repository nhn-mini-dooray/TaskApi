package com.nhnacademy.mini_dooray.task_api.repository;

import com.nhnacademy.mini_dooray.task_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
