package com.nhnacademy.mini_dooray.task_api.project_member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "project_status_id", nullable = false)
    private ProjectStatus projectStatusId;

    public Project (String projectName, Long accountId, ProjectStatus projectStatusId) {
        this.projectName = projectName;
        this.accountId = accountId;
        this.projectStatusId = projectStatusId;
    }

}
