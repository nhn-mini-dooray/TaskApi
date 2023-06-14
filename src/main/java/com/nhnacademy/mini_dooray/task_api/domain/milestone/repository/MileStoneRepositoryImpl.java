package com.nhnacademy.mini_dooray.task_api.domain.milestone.repository;


import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.QMileStone;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.model.MileStoneDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.QProject;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MileStoneRepositoryImpl implements MileStoneRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 프로젝트 아이디로 마일스톤 조회
     *
     * @param projectId
     * @return
     */
    @Override
    public List<MileStoneDTO> getMileStoneByProjectId(Long projectId) {
        QMileStone mileStone = QMileStone.mileStone;
        QProject project = QProject.project;

        return jpaQueryFactory
                .select(Projections.constructor(MileStoneDTO.class,
                        mileStone.projectId.projectId,
                        mileStone.mileStoneName,
                        mileStone.mileStoneStartDate,
                        mileStone.mileStoneEndDate))
                .from(mileStone)
                .join(mileStone.projectId, project)
                .where(project.projectId.eq(projectId))
                .fetch();
    }
}
