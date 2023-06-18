package com.nhnacademy.mini_dooray.task_api.domain.project.repository;

import com.nhnacademy.mini_dooray.task_api.domain.member.entity.QMember;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.QProject;
import com.nhnacademy.mini_dooray.task_api.domain.project.model.ProjectResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ProjectResponseDTO> getProjectForMember(Long accountId) {
        QMember member = QMember.member;
        QProject project = QProject.project;

        return jpaQueryFactory
                .select(Projections.constructor(ProjectResponseDTO.class,
                        project.projectId,
                        project.projectName,
                        project.projectStatusId.projectStatusName))
                .from(member)
                .join(member.pk.project, project)
                .where(member.pk.accountId.eq(accountId))
                .fetch();
    }
}
