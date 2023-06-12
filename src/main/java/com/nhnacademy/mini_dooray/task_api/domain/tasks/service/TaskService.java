package com.nhnacademy.mini_dooray.task_api.domain.tasks.service;

import com.nhnacademy.mini_dooray.task_api.domain.comments.repository.CommentRepository;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.entity.MileStone;
import com.nhnacademy.mini_dooray.task_api.domain.milestone.repository.MileStoneRepository;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.domain.task_tag.repository.TaskTagRepository;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.entity.Task;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request.CreateTaskRequestDto;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request.UpdateTaskRequestDto;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.response.TaskResponseDto;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.repository.TaskRepository;
import com.nhnacademy.mini_dooray.task_api.exception.NotFoundException;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request.UpdateTaskRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final CommentRepository commentRepository;
    private final TaskTagRepository taskTagRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MileStoneRepository mileStoneRepository;

    /**
     * Task 조회
     *
     * @param id
     * @return
     */
    public TaskResponseDto select(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(NotFoundException::new);
        return TaskResponseDto.fromEntity(task);
    }

    /**
     * Task 생성
     *
     * @param requestDto
     */
    public void create(CreateTaskRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(NotFoundException::new);
        MileStone mileStone = mileStoneRepository.findById(requestDto.getMileStoneId()).orElseThrow(NotFoundException::new);
        taskRepository.save(CreateTaskRequestDto.toEntity(requestDto, project, mileStone));

    }

    /**
     * Task 수정
     *
     * @param id
     * @param requestDto
     */
    public void update(Long id, UpdateTaskRequestDto requestDto) {
        Task task = taskRepository.findById(id).orElseThrow(NotFoundException::new);
        task.setTaskName(requestDto.getName());
        task.setTaskContent(requestDto.getContent());
    }

    /**
     * Task 삭제
     *
     * @param id
     */
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
