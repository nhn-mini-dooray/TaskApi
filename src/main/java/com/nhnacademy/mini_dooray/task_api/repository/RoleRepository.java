package com.nhnacademy.mini_dooray.task_api.repository;

import com.nhnacademy.mini_dooray.task_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
