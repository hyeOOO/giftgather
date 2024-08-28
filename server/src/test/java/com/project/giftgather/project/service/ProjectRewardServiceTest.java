package com.project.giftgather.project.service;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.ProjectReward;
import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.dto.ProjectRewardDTO;
import com.project.giftgather.project.repository.ProjectRepository;
import com.project.giftgather.project.repository.ProjectRewardRepository;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProjectRewardServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectRewardService projectRewardService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectRewardRepository projectRewardRepository;

    @Autowired
    private EntityManager em;

    @Test
    void 리워드_생성() {
        // Given: 프로젝트를 생성합니다.
        ProjectDTO createdProjectDTO = projectService.createProject(); // 프로젝트 생성
        String projectId = createdProjectDTO.getProjectId(); // 생성된 프로젝트의 ID

        em.flush(); // DB에 반영
        em.clear(); // 영속성 컨텍스트 초기화

        // 리워드 DTO 생성
        ProjectRewardDTO rewardDTO = new ProjectRewardDTO();
        rewardDTO.setDescription("Test Reward Description");
        rewardDTO.setAmount(new BigDecimal("100.00"));
        rewardDTO.setQuantity(10);
        rewardDTO.setDeliveryDate(LocalDateTime.now().plusMonths(1));

        // When: 생성된 프로젝트에 대해 리워드 생성
        ProjectRewardDTO createdReward = projectRewardService.createReward(projectId, rewardDTO);

        // Then: 생성된 리워드를 DB에서 조회하여 검증
        ProjectReward rewardById = projectRewardRepository.findById(createdReward.getRewardId())
                .orElseThrow(() -> new AssertionError("리워드를 찾을 수 없습니다."));

        // 리워드 필드 검증
        assertThat(rewardById.getRewardId()).isEqualTo(createdReward.getRewardId());
        assertThat(rewardById.getDescription()).isEqualTo(rewardDTO.getDescription());
        assertThat(rewardById.getAmount()).isEqualByComparingTo(rewardDTO.getAmount());
        assertThat(rewardById.getQuantity()).isEqualTo(rewardDTO.getQuantity());
        assertThat(rewardById.getDeliveryDate()).isEqualTo(rewardDTO.getDeliveryDate());

        // 프로젝트 필드 검증: ProjectRepository를 사용하여 프로젝트 검증
        Project projectById = projectRepository.findById(projectId)
                .orElseThrow(() -> new AssertionError("프로젝트를 찾을 수 없습니다."));

        assertThat(rewardById.getProject().getProjectId()).isEqualTo(projectById.getProjectId());
        assertThat(projectById.getProjectId()).isEqualTo(projectId);
    }
}