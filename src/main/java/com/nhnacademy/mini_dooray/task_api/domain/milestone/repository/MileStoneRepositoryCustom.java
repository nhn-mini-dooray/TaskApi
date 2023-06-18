package com.nhnacademy.mini_dooray.task_api.domain.milestone.repository;

import com.nhnacademy.mini_dooray.task_api.domain.milestone.model.MileStoneDTO;

import java.util.List;

public interface MileStoneRepositoryCustom {
    List<MileStoneDTO> getMileStoneByProjectId(Long projectId);
}
