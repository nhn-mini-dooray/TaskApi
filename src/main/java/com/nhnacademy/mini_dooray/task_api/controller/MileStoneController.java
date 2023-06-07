package com.nhnacademy.mini_dooray.task_api.controller;

import com.nhnacademy.mini_dooray.task_api.dto.MileStoneDTO;
import com.nhnacademy.mini_dooray.task_api.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/milestones")
@RequiredArgsConstructor
public class MileStoneController {

    private final MileStoneService mileStoneService;

    /**
     * 마일스톤 생성
     *
     * @param mileStoneDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<MileStoneDTO> createMileStone(@RequestBody MileStoneDTO mileStoneDTO) {
        MileStoneDTO createdMileStone = mileStoneService.createMileStone(mileStoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMileStone);
    }


}
