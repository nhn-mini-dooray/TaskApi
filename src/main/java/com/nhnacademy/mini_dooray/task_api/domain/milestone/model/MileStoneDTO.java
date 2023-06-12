package com.nhnacademy.mini_dooray.task_api.domain.milestone.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileStoneDTO {
    private Long projectId;
    private String mileStoneName;
    private LocalDate mileStoneStartDate;
    private LocalDate mileStoneEndDate;
}
