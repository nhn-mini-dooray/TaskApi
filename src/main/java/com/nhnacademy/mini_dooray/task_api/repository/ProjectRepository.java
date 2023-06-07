package com.nhnacademy.mini_dooray.task_api.project_member.repository;

import com.nhnacademy.mini_dooray.task_api.project_member.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
