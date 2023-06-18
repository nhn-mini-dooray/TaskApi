package com.nhnacademy.mini_dooray.task_api.domain.member.repository;

import com.nhnacademy.mini_dooray.task_api.domain.member.entity.QMember;
import com.nhnacademy.mini_dooray.task_api.domain.member.model.MemberDTO;
import com.nhnacademy.mini_dooray.task_api.domain.role.entity.QRole;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<MemberDTO> getMembersForProject(Long projectId) {
        QMember member = QMember.member;
        QRole role = QRole.role;

        return jpaQueryFactory
                .select(Projections.constructor(MemberDTO.class,
                        member.pk.accountId,
                        member.pk.project.projectId,
                        role.roleId))
                .from(member)
                .where(member.pk.project.projectId.eq(projectId))
                .fetch();
    }
}
