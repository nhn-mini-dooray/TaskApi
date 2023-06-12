package com.nhnacademy.mini_dooray.task_api.domain.task_tag.entity;

import com.nhnacademy.mini_dooray.task_api.domain.tag.entity.Tag;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "task_tag")
public class TaskTag {

    @EmbeddedId
    private Pk pk;

    @MapsId("taskId")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_id")
    private Task task;

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Pk implements Serializable {

        @Column(name = "task_id")
        private Long taskId;

        @Column(name = "tag_id")
        private Long tagId;
    }
}
