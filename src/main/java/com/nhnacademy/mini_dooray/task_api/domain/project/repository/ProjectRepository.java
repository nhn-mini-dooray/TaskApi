package com.nhnacademy.mini_dooray.task_api.domain.project.repository;

import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {
}
