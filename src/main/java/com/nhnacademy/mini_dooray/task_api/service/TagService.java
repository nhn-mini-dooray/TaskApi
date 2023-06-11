package com.nhnacademy.mini_dooray.task_api.service;

import com.nhnacademy.mini_dooray.task_api.dto.TagDTO;
import com.nhnacademy.mini_dooray.task_api.entity.Project;
import com.nhnacademy.mini_dooray.task_api.entity.QTag;
import com.nhnacademy.mini_dooray.task_api.entity.Tag;
import com.nhnacademy.mini_dooray.task_api.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.repository.TagRepository;
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
        QTag tag = QTag.tag;

        return jpaQueryFactory
                .select(Projections.constructor(TagDTO.class,
                        tag.projectId.projectId,
                        tag.tagName))
                .from(tag)
                .where(tag.projectId.projectId.eq(projectId))
                .fetch();
    }
}
