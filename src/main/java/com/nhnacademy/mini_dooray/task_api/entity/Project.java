package com.nhnacademy.mini_dooray.task_api.project.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "project_status_id", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;
}
