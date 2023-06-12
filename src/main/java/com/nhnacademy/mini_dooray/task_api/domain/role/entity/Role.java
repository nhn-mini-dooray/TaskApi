package com.nhnacademy.mini_dooray.task_api.domain.role.entity;

import javax.persistence.*;

import lombok.*;

@Getter
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column (name = "role_name", nullable = false)
    private String roleName;
}
