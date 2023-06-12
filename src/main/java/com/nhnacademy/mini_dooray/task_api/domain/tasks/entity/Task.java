package com.nhnacademy.mini_dooray.task_api.domain.tasks.entity;

import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.MileStone;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @ManyToOne
    @JoinColumn(name = "mile_stone_id")
    private MileStone mileStone;

    @Setter
    @NotNull
    @Length(max = 45)
    @Column(name = "task_name")
    private String taskName;

    @Setter
    @NotNull
    @Length(max = 200)
    @Column(name = "task_content")
    private String taskContent;

    public Task(Project project, MileStone mileStone, String taskName, String taskContent) {
        this.taskId = null;
        this.project = project;
        this.mileStone = mileStone;
        this.taskName = taskName;
        this.taskContent = taskContent;
    }
}
