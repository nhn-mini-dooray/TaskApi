package com.nhnacademy.mini_dooray.task_api.domain.tag.repository;


import com.nhnacademy.mini_dooray.task_api.domain.tag.entity.QTag;
import com.nhnacademy.mini_dooray.task_api.domain.tag.model.TagDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<TagDTO> getTagsByProjectId(Long projectId) {
        QTag tag = QTag.tag;

        return jpaQueryFactory
                .select(Projections.constructor(TagDTO.class,
                        tag.projectId.projectId,
                        tag.tagName))
                .from(tag)
                .where(tag.projectId.projectId.eq(projectId))
                .fetch();
    }
}