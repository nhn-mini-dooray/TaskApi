package com.nhnacademy.mini_dooray.task_api.domain.tag.repository;

import com.nhnacademy.mini_dooray.task_api.domain.tag.model.TagDTO;

import java.util.List;

public interface TagRepositoryCustom {
    List<TagDTO> getTagsByProjectId(Long projectId);
}
