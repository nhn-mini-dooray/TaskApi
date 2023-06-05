package com.nhnacademy.mini_dooray.task_api.repository;


import com.nhnacademy.mini_dooray.task_api.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
