package com.nhnacademy.mini_dooray.task_api.domain.projectStatus.repository;

import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
