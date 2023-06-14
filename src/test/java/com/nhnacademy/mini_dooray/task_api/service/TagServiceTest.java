package com.nhnacademy.mini_dooray.task_api.domain.tag.service;

import com.nhnacademy.mini_dooray.task_api.domain.project.entity.Project;
import com.nhnacademy.mini_dooray.task_api.domain.project.repository.ProjectRepository;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatus;
import com.nhnacademy.mini_dooray.task_api.domain.projectStatus.entity.ProjectStatusName;
import com.nhnacademy.mini_dooray.task_api.domain.tag.entity.Tag;
import com.nhnacademy.mini_dooray.task_api.domain.tag.model.TagDTO;
import com.nhnacademy.mini_dooray.task_api.domain.tag.repository.TagRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private JPAQueryFactory jpaQueryFactory;

    @InjectMocks
    private TagService tagService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTag() {
        Long projectId = 1L;
        String tagName = "Test Tag";
        TagDTO tagDTO = new TagDTO(projectId, tagName);

        Project project = new Project("Test Project", 1L,
                new ProjectStatus(1, ProjectStatusName.ACTIVE));
        given(projectRepository.save(ArgumentMatchers.any(Project.class))).willReturn(project);
        given(projectRepository.findById(1L)).willReturn(Optional.of(project));

        Tag tag = new Tag(project, tagName);
        given(projectRepository.findById(projectId)).willReturn(Optional.of(project));
        given(tagRepository.save(any(Tag.class))).willReturn(tag);

        TagDTO createdTag = tagService.createTag(tagDTO);

        assertEquals(projectId, createdTag.getProjectId());
        assertEquals(tagName, createdTag.getTagName());
    }

    @Test
    void testGetTagsByProjectId() {
    }

    @Test
    void testUpdateTag() {
    }

    @Test
    void testDeleteTag() {
    }
}