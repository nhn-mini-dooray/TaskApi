package com.nhnacademy.mini_dooray.task_api.repository;

import com.nhnacademy.mini_dooray.task_api.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
