package com.project.giftgather.project.service;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProjectServiceTest {

    @Autowired ProjectService projectService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    EntityManager em;

    @Test
    void 프로젝트생성() {
        //given
        ProjectDTO project = projectService.createProject();
        System.out.println(project.getProjectId());

        //when
        Project projectById = projectRepository.findById(project.getProjectId())
                .orElseThrow(() -> new AssertionError("Project not found"));

        //then
        Assertions.assertThat(project.getProjectId()).isEqualTo(projectById.getProjectId());
    }
}