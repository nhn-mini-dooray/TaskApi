package com.nhnacademy.mini_dooray.task_api.domain.tag.repository;

import com.nhnacademy.mini_dooray.task_api.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
