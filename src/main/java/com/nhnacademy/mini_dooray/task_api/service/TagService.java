package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.dto.TagDTO;
import com.nhnacademy.mini_dooray.task_api.entity.Project;
import com.nhnacademy.mini_dooray.task_api.entity.Tag;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    /**
     * Tag 생성
     *
     * @param tagDTO
     * @return
     */
    public TagDTO createTag(TagDTO tagDTO) {

        Project projectId = projectRepository.findById(tagDTO.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("projectId Not Found"));
        Tag tag = new Tag(projectId, tagDTO.getTagName());

        Tag savedTag = tagRepository.save(tag);

        return new TagDTO(savedTag.getProjectId().getProjectId(),savedTag.getTagName());
    }
}
