package com.nhnacademy.mini_dooray.task_api.domain.role.repository;

import com.nhnacademy.mini_dooray.task_api.domain.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
