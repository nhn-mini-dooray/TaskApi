package com.nhnacademy.mini_dooray.task_api.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @Column (name = "tag_name")
    private String tagName;

    public Tag(Project projectId, String tagName) {
        this.projectId = projectId;
        this.tagName = tagName;
    }
}
