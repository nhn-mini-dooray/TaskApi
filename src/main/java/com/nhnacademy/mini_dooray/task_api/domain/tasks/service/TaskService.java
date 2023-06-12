package com.nhnacademy.mini_dooray.account_api.domain.tasks.service;

import com.nhnacademy.mini_dooray.account_api.domain.mile_stones.entity.MileStone;
import com.nhnacademy.mini_dooray.account_api.domain.mile_stones.repository.MileStoneRepository;
import com.nhnacademy.mini_dooray.account_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.account_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.account_api.domain.tasks.entity.Task;
import com.nhnacademy.mini_dooray.account_api.domain.tasks.model.request.CreateTaskRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.tasks.model.request.UpdateTaskRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.tasks.model.response.TaskResponseDto;
import com.nhnacademy.mini_dooray.account_api.domain.tasks.repository.TaskRepository;
import com.nhnacademy.mini_dooray.account_api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MileStoneRepository mileStoneRepository;

    public TaskResponseDto select(Long id){
        Task task = taskRepository.findById(id).orElseThrow(NotFoundException::new);
        return TaskResponseDto.fromEntity(task);
    }

    public void create(CreateTaskRequestDto requestDto){
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(NotFoundException::new);
        MileStone mileStone = mileStoneRepository.findById(requestDto.getMileStoneId()).orElseThrow(NotFoundException::new);
        taskRepository.save(CreateTaskRequestDto.toEntity(requestDto,project,mileStone));

    }

    public void update(Long id, UpdateTaskRequestDto requestDto){
        Task task = taskRepository.findById(id).orElseThrow(NotFoundException::new);
        task.setTaskName(requestDto.getName());
        task.setTaskContent(requestDto.getContent());
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }

}
