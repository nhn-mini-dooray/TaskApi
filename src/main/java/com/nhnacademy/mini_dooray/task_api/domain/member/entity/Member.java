package com.nhnacademy.mini_dooray.task_api.domain.member.entity;

import javax.persistence.*;

import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.role.entity.Role;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "account_id")
        private Long accountId;

        @ManyToOne
        @JoinColumn(name = "project_id", nullable = false)
        private Project project;
    }
}
