package com.nhnacademy.mini_dooray.task_api.controller;

import com.nhnacademy.mini_dooray.task_api.dto.TagDTO;
import com.nhnacademy.mini_dooray.task_api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    /**
     * Tag 생성
     * @param tagDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        TagDTO createdTag = tagService.createTag(tagDTO);
        URI locaion = UriComponentsBuilder.fromPath("/{tagId}")
                .buildAndExpand(createdTag.getTagName())
                .toUri();
        return ResponseEntity.created(locaion).body(createdTag);
    }

    /**
     * projectId로 Tag 조회
     * @param projectId
     * @return
     */
    @GetMapping("{projectId}")
    public ResponseEntity<List<TagDTO>> getTagsByProjectId(@PathVariable Long projectId) {
        List<TagDTO> tagDTOs = tagService.getTagsByProjectId(projectId);
        return ResponseEntity.ok(tagDTOs);
    }

    /**
     * Tag 수정
     * @param tagId
     * @param tagDTO
     * @return
     */
    @PutMapping("/{tagId}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long tagId, @RequestBody TagDTO tagDTO) {
        TagDTO updatedTagDTO = tagService.updateTag(tagId, tagDTO);
        return ResponseEntity.ok(updatedTagDTO);
    }
}
