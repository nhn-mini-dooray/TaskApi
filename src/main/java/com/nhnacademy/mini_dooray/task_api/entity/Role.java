package com.nhnacademy.mini_dooray.task_api.entity;

import javax.persistence.*;

import lombok.*;

@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column (name = "role_name", nullable = false)
    private String roleName;

}
