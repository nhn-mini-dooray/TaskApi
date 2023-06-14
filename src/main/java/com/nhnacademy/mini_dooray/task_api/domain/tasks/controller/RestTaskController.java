package com.nhnacademy.mini_dooray.task_api.domain.tasks.controller;


import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request.UpdateTaskRequestDto;
import com.nhnacademy.mini_dooray.task_api.exception.ValidationFailedException;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.request.CreateTaskRequestDto;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.model.response.TaskResponseDto;
import com.nhnacademy.mini_dooray.task_api.domain.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class RestTaskController {
    private final TaskService taskService;

    @GetMapping("/{id}/select")
    public ResponseEntity<TaskResponseDto> selectTask(@PathVariable(name = "id") Long id) {

        TaskResponseDto responseDto = taskService.select(id);

        return ResponseEntity
                .ok(responseDto);
    }

    @GetMapping("/project/{projectId}")
    public List<TaskResponseDto> getTaskByProjectId(@PathVariable Long projectId) {
        return taskService.getTaskByProjectId(projectId);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTask(@Valid @RequestBody CreateTaskRequestDto requestDto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        taskService.create(requestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateTask(@PathVariable(name = "id") Long id,
                                           @Valid @RequestBody UpdateTaskRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        taskService.update(id, requestDto);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteTask(@PathVariable(name = "id") Long id) {
        taskService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
