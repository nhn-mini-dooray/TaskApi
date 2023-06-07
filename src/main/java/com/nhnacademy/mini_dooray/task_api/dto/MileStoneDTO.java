package com.nhnacademy.mini_dooray.task_api.dto;

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
