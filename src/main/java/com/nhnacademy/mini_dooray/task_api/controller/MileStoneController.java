package com.nhnacademy.mini_dooray.task_api.controller;

import com.nhnacademy.mini_dooray.task_api.dto.MileStoneDTO;
import com.nhnacademy.mini_dooray.task_api.service.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 마일스톤 조회
     *
     * @param mileStoneId
     * @return
     */
    @GetMapping("/{mileStoneId}")
    public ResponseEntity<MileStoneDTO> getMileStone(@PathVariable Long mileStoneId) {
        MileStoneDTO mileStoneDTO = mileStoneService.getMileStone(mileStoneId);
        return ResponseEntity.ok(mileStoneDTO);
    }

    /**
     * 마일스톤 수정
     *
     * @param mileStoneId
     * @param mileStoneDTO
     * @return
     */
    @PutMapping("/{mileStoneId}")
    public ResponseEntity<MileStoneDTO> updateMileStone(@PathVariable Long mileStoneId, @RequestBody MileStoneDTO mileStoneDTO) {
        MileStoneDTO updatedMileStoneDTO = mileStoneService.updateMileStone(mileStoneId, mileStoneDTO);
        return ResponseEntity.ok(updatedMileStoneDTO);
    }

    /**
     * 마일스톤 삭제
     *
     * @param mileStoneId
     * @return
     */
    @DeleteMapping("/{mileStoneId}")
    public ResponseEntity<Void> deleteMileStone(@PathVariable Long mileStoneId) {
        mileStoneService.deleteMileStone(mileStoneId);
        return ResponseEntity.noContent().build();
    }
}
