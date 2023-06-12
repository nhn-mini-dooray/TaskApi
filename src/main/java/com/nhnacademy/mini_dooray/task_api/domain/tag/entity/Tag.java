package com.nhnacademy.mini_dooray.task_api.domain.tag.entity;


import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    @NotNull
    @Length(max = 45)
    @Column(name = "tag_name")
    private String tagName;

    public Tag(Project projectId, String tagName) {
        this.projectId = projectId;
        this.tagName = tagName;
    }
}
