package com.nhnacademy.mini_dooray.account_api.domain.tasks.repository;

import com.nhnacademy.mini_dooray.account_api.domain.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
