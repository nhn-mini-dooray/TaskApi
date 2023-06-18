package com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "project_status")
public class ProjectStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "project_status_id")
    private Integer projectStatusId;

    @Enumerated(EnumType.STRING)
    @Column (name = "project_status_name")
    private ProjectStatusName projectStatusName;
}
