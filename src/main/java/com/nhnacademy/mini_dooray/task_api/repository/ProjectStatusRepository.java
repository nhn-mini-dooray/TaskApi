package com.nhnacademy.mini_dooray.task_api.project_member.repository;

import com.nhnacademy.mini_dooray.task_api.project_member.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
