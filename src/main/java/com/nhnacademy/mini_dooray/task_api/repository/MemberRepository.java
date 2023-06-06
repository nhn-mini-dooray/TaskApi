package com.nhnacademy.mini_dooray.task_api.repository;

import com.nhnacademy.mini_dooray.task_api.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Member.Pk> {
    List<Member> findByPkAccountId(Long accountId);
}
