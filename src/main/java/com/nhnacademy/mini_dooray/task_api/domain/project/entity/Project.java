package com.nhnacademy.mini_dooray.task_api.domain.project.entity;

import javax.persistence.*;

import com.nhnacademy.mini_dooray.task_api.domain.member.entity.Member;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
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

    public void updateProjectName(String projectName) {
        if (projectName != null) {
            this.projectName = projectName;
        }
    }

    @OneToMany(mappedBy = "pk.project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();
}