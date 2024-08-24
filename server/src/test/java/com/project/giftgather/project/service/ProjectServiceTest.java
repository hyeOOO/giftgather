package com.project.giftgather.project.service;

import com.project.giftgather.project.domain.Category;
import com.project.giftgather.project.domain.CategoryMapping;
import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.nosql.ProjectDetail;
import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.dto.ProjectDetailDTO;
import com.project.giftgather.project.dto.ProjectDocumentDTO;
import com.project.giftgather.project.dto.ProjectUpdateRequest;
import com.project.giftgather.project.repository.CategoryMappingRepository;
import com.project.giftgather.project.repository.CategoryRepository;
import com.project.giftgather.project.repository.ProjectDetailRepository;
import com.project.giftgather.project.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProjectServiceTest {

    @Autowired ProjectService projectService;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectDetailRepository projectDetailRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMappingRepository categoryMappingRepository;
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
        ProjectDetail projectDetail = projectDetailRepository.findByProjectId(project.getProjectId())
                .orElseThrow(() -> new AssertionError("Project not found"));

        //then
        assertThat(project.getProjectId()).isEqualTo(projectById.getProjectId());
        assertThat(project.getProjectDetail().getProjectId()).isEqualTo(projectDetail.getProjectId());
    }

    @Test
    void 프로젝트_정보_업데이트() {
        //given
        ProjectDTO project = projectService.createProject();
        em.flush(); //DB에 데이터 강제 반영
        em.clear(); // 영속성 컨텍스트 초기화

        //카테고리 추가
        Category category = Category.createCategory("Test Category");
        categoryRepository.save(category);

        //문서 DTO 리스트
        List<ProjectDocumentDTO> documentDTOS = List.of(
                new ProjectDocumentDTO(null, "Document 1", "http://example.com/doc1", LocalDateTime.now(), LocalDateTime.now()),
                new ProjectDocumentDTO(null, "Document 2", "http://example.com/doc2", LocalDateTime.now(), LocalDateTime.now())
        );

        //when
        String updatedTitle = "Updated Project Title";
        BigDecimal updatedGoalAmount = new BigDecimal("5000");

        ProjectUpdateRequest updateRequest = new ProjectUpdateRequest();
        updateRequest.setGoalAmount(updatedGoalAmount);
        updateRequest.setCategoryId(category.getCategoryId());
        updateRequest.setDocuments(documentDTOS);

        ProjectDTO updatedProject = projectService.updateProjectInfo(project.getProjectId(), updateRequest);

        //then
        Project projectById = projectRepository.findById(project.getProjectId())
                .orElseThrow(() -> new AssertionError("프로젝트를 찾을 수 없습니다. "));

        //프로젝트 기본 정보 검증
        assertThat(projectById.getTitle()).isEqualTo(updatedProject.getTitle());
        assertThat(projectById.getGoalAmount()).isEqualTo(updatedProject.getGoalAmount());

        //카테고리 검증
        //db에 더미데이터가 2개 있어서 사이즈가 3이고 get의 인덱스도 2
        List<CategoryMapping> categoryMappings = categoryMappingRepository.findAll();
        assertThat(categoryMappings.size()).isEqualTo(3);
        assertThat(categoryMappings.get(2).getCategory().getCategoryName()).isEqualTo(category.getCategoryName());

        //문서 검증
        Optional<ProjectDetail> projectDetail = projectDetailRepository.findByProjectId(project.getProjectId());
        Assertions.assertThat(projectDetail).isPresent();
        Assertions.assertThat(projectDetail.get().getDocuments().size()).isEqualTo(2);
        Assertions.assertThat(projectDetail.get().getDocuments().get(0).getName()).isEqualTo("Document 1");
        Assertions.assertThat(projectDetail.get().getDocuments().get(0).getUrl()).isEqualTo("http://example.com/doc1");
    }
}