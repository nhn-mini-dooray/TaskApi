package com.nhnacademy.mini_dooray.task_api.domain.milestone.repository;

import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileStoneRepository extends JpaRepository<MileStone, Long> {
}
