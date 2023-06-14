package com.nhnacademy.mini_dooray.task_api.domain.tag.service;

import com.nhnacademy.mini_dooray.task_api.domain.tag.model.TagDTO;
import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.tag.entity.QTag;
import com.nhnacademy.mini_dooray.task_api.domain.tag.entity.Tag;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.domain.tag.repository.TagRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;
    private final JPAQueryFactory jpaQueryFactory;

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

    /**
     * projectId로 Tag 조회
     *
     * @param projectId
     * @return
     */
    public List<TagDTO> getTagsByProjectId(Long projectId) {
        return tagRepository.getTagsByProjectId(projectId);
    }

    /**
     * Tag 수정
     *
     * @param tagId
     * @param tagDTO
     * @return
     */
    public TagDTO updateTag(Long tagId, TagDTO tagDTO) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Tag Not Found"));

        tag.setTagName(tagDTO.getTagName());

        Tag updateTag = tagRepository.save(tag);

        return new TagDTO(
                updateTag.getProjectId().getProjectId(),
                updateTag.getTagName()
        );
    }

    /**
     * Tag 삭제
     *
     * @param tagId
     */
    public void deleteTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new IllegalArgumentException("Tag Not Found"));

        tagRepository.delete(tag);
    }
}
