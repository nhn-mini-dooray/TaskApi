package com.nhnacademy.mini_dooray.task_api.domain.milestone.service;

import com.nhnacademy.mini_dooray.task_api.domain.milestone.model.MileStoneDTO;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.MileStone;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.repository.MileStoneRepository;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MileStoneService {

    private final MileStoneRepository mileStoneRepository;
    private final ProjectRepository projectRepository;

    /**
     * 마일스톤 생성
     *
     * @param mileStoneDTO
     * @return
     */
    public MileStoneDTO createMileStone(MileStoneDTO mileStoneDTO) {
        Project projectId = projectRepository.findById(mileStoneDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project Not Found"));

        MileStone mileStone = new MileStone(projectId, mileStoneDTO.getMileStoneName(), mileStoneDTO.getMileStoneStartDate(), mileStoneDTO.getMileStoneEndDate());

        MileStone savedMileStone = mileStoneRepository.save(mileStone);

        return new MileStoneDTO(savedMileStone.getProjectId().getProjectId(),
                savedMileStone.getMileStoneName(), savedMileStone.getMileStoneStartDate(), savedMileStone.getMileStoneEndDate());
    }

    /**
     * 마일스톤 조회
     *
     * @param mileStoneId
     * @return
     */
    public MileStoneDTO getMileStone(Long mileStoneId) {
        MileStone mileStone = mileStoneRepository.findById(mileStoneId)
                .orElseThrow(() -> new IllegalArgumentException("MileStone Not Found"));

        return new MileStoneDTO(
                mileStone.getProjectId().getProjectId(),
                mileStone.getMileStoneName(),
                mileStone.getMileStoneStartDate(),
                mileStone.getMileStoneEndDate()
        );
    }

    /**
     * 프로젝트 아이디로 마일스톤 조회
     *
     * @param projectId
     * @return
     */
    public List<MileStoneDTO> getMileStoneByProjectId (Long projectId) {
        return mileStoneRepository.getMileStoneByProjectId(projectId);
    }

    /**
     * 마일스톤 수정
     *
     * @param mileStoneId
     * @param mileStoneDTO
     * @return
     */
    public MileStoneDTO updateMileStone(Long mileStoneId, MileStoneDTO mileStoneDTO) {
        MileStone mileStone = mileStoneRepository.findById(mileStoneId)
                .orElseThrow(() -> new IllegalArgumentException("MileStone Not Found"));

        mileStone.setMileStoneName(mileStoneDTO.getMileStoneName());
        mileStone.setMileStoneStartDate(mileStoneDTO.getMileStoneStartDate());
        mileStone.setMileStoneEndDate(mileStoneDTO.getMileStoneEndDate());

        MileStone updatedMileStone = mileStoneRepository.save(mileStone);

        return new MileStoneDTO(
                updatedMileStone.getProjectId().getProjectId(),
                updatedMileStone.getMileStoneName(),
                updatedMileStone.getMileStoneStartDate(),
                updatedMileStone.getMileStoneEndDate()
        );
    }

    /**
     * 마일스톤 삭제
     *
     * @param mileStoneId
     */
    public void deleteMileStone(Long mileStoneId) {
        MileStone mileStone = mileStoneRepository.findById(mileStoneId)
                .orElseThrow(() -> new IllegalArgumentException("MileStone Not Found"));

        mileStoneRepository.delete(mileStone);
    }
}
