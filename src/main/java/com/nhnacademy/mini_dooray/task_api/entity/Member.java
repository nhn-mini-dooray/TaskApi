package com.nhnacademy.mini_dooray.task_api.entity;

import javax.persistence.*;
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


    @Column(name = "role_id")
    private  Integer role;

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
