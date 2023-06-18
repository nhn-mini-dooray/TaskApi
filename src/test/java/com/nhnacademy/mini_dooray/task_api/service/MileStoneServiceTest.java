package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.domain.milestone.model.MileStoneDTO;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.MileStone;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.service.MileStoneService;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatusName;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.repository.MileStoneRepository;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@SpringBootTest
class MileStoneServiceTest {

    @Mock
    private MileStoneRepository mileStoneRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Autowired
    private MileStoneService mileStoneService;

    @BeforeEach
    public void setUp() {
        mileStoneService = new MileStoneService(mileStoneRepository, projectRepository);
    }

    @Test
    void testCreateMileStone() {

        LocalDate startDate = LocalDate.parse("2023-06-23");
        LocalDate endDate = LocalDate.parse("2023-06-30");

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(ArgumentMatchers.any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));

        MileStoneDTO mileStoneDTO = new MileStoneDTO(1L, "Milestone 1", startDate, endDate);
        MileStone savedMileStone = new MileStone(project, "Milestone 1", startDate, endDate);
        when(mileStoneRepository.save(any(MileStone.class))).thenReturn(savedMileStone);

        MileStoneDTO createdMileStone = mileStoneService.createMileStone(mileStoneDTO);

        assertEquals("Milestone 1", createdMileStone.getMileStoneName());
        assertEquals(startDate, createdMileStone.getMileStoneStartDate());
        assertEquals(endDate, createdMileStone.getMileStoneEndDate());
    }

    @Test
    void testGetMileStone() {

        Long mileStoneId = 1L;
        String mileStoneName = "Milestone 1";
        LocalDate startDate = LocalDate.parse("2023-06-23");
        LocalDate endDate = LocalDate.parse("2023-06-30");

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(ArgumentMatchers.any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));

        MileStone existingMileStone = new MileStone(project, mileStoneName, startDate, endDate);

        given(mileStoneRepository.findById(mileStoneId)).willReturn(Optional.of(existingMileStone));

        MileStoneDTO result = mileStoneService.getMileStone(mileStoneId);

        assertNotNull(result);
        assertEquals(mileStoneName, result.getMileStoneName());
        assertEquals(startDate, result.getMileStoneStartDate());
        assertEquals(endDate, result.getMileStoneEndDate());

    }

    @Test
    void testUpdateMileStone() {
        Long mileStoneId = 1L;
        String updatedName = "Updated Milestone";
        LocalDate updatedStartDate = LocalDate.parse("2023-06-25");
        LocalDate updatedEndDate = LocalDate.parse("2023-07-02");

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(ArgumentMatchers.any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));

        MileStoneDTO mileStoneDTO = new MileStoneDTO(project.getProjectId(), updatedName, updatedStartDate, updatedEndDate);
        MileStone existingMileStone = new MileStone(project, "Milestone 1", LocalDate.parse("2023-06-23"), LocalDate.parse("2023-06-30"));
        MileStone updatedMileStone = new MileStone(project, updatedName, updatedStartDate, updatedEndDate);

        given(mileStoneRepository.findById(mileStoneId)).willReturn(Optional.of(existingMileStone));
        given(mileStoneRepository.save(any(MileStone.class))).willReturn(updatedMileStone);

        MileStoneDTO result = mileStoneService.updateMileStone(mileStoneId, mileStoneDTO);

        assertNotNull(result);
        assertEquals(updatedName, result.getMileStoneName());
        assertEquals(updatedStartDate, result.getMileStoneStartDate());
        assertEquals(updatedEndDate, result.getMileStoneEndDate());
    }

    @Test
    void testDeleteMileStone() {

        Long mileStoneId = 1L;
        String mileStoneName = "Milestone 1";
        LocalDate startDate = LocalDate.parse("2023-06-23");
        LocalDate endDate = LocalDate.parse("2023-06-30");

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(ArgumentMatchers.any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));

        MileStone existingMileStone = new MileStone(project, mileStoneName, startDate, endDate);

        given(mileStoneRepository.findById(mileStoneId)).willReturn(Optional.of(existingMileStone));

        mileStoneService.deleteMileStone(mileStoneId);

        verify(mileStoneRepository, times(1)).delete(existingMileStone);
    }
}