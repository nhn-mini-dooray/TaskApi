package com.nhnacademy.mini_dooray.task_api.controller;

import com.nhnacademy.mini_dooray.task_api.dto.TagDTO;
import com.nhnacademy.mini_dooray.task_api.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
