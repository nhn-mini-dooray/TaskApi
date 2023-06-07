package com.nhnacademy.mini_dooray.task_api.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mile_stone")
public class MileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mile_stone_id")
    private Long mileStoneId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project projectId;

    @Column(name = "mile_stone_name")
    private String mileStoneName;

    @Column(name = "mile_stone_start_date")
    private LocalDate mileStoneStartDate;

    @Column(name = "mile_stone_end_date")
    private LocalDate mileStoneEndDate;

    public MileStone(Project projectId, String mileStoneName, LocalDate mileStoneStartDate, LocalDate mileStoneEndDate) {
        this.projectId = projectId;
        this.mileStoneName = mileStoneName;
        this.mileStoneStartDate = mileStoneStartDate;
        this.mileStoneEndDate = mileStoneEndDate;
    }
}
