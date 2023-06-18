package com.nhnacademy.mini_dooray.task_api.domain.milestone.entity;

import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mile_stones")
public class MileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mile_stone_id")
    private Long mileStoneId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @Setter
    @Column(name = "mile_stone_name")
    private String mileStoneName;

    @Setter
    @Column(name = "mile_stone_start_date")
    private LocalDate mileStoneStartDate;

    @Setter
    @Column(name = "mile_stone_end_date")
    private LocalDate mileStoneEndDate;

    public MileStone(Project projectId, String mileStoneName, LocalDate mileStoneStartDate, LocalDate mileStoneEndDate) {
        this.projectId = projectId;
        this.mileStoneName = mileStoneName;
        this.mileStoneStartDate = mileStoneStartDate;
        this.mileStoneEndDate = mileStoneEndDate;
    }
}
