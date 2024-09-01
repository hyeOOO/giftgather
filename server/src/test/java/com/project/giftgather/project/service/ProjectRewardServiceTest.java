package com.project.giftgather.project.service;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.ProjectReward;
import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.dto.ProjectRewardDTO;
import com.project.giftgather.project.repository.ProjectRepository;
import com.project.giftgather.project.repository.ProjectRewardRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void 리워드_조회 () {
        //given 프로젝트와 리워드 생성
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

        // 리워드 생성
        ProjectRewardDTO createdReward = projectRewardService.createReward(projectId, rewardDTO);
        String rewardId = createdReward.getRewardId(); // 생성된 리워드의 ID

        em.flush(); // DB에 반영
        em.clear(); // 영속성 컨텍스트 초기화

        //when 생성된 리워드를 조회
        ProjectRewardDTO foundReward = projectRewardService.getReward(rewardId);

        //then 생성한 리워드와 조회된 리워드 비교
        assertThat(foundReward.getRewardId()).isEqualTo(rewardId);
        assertThat(foundReward.getDescription()).isEqualTo(rewardDTO.getDescription());
        assertThat(foundReward.getAmount()).isEqualByComparingTo(rewardDTO.getAmount());
        assertThat(foundReward.getQuantity()).isEqualTo(rewardDTO.getQuantity());
        assertThat(foundReward.getDeliveryDate()).isEqualTo(rewardDTO.getDeliveryDate());
    }

    @Test
    @Transactional
    void 존재하지_않는_리워드_조회_예외 () {
        // Given: 잘못된 리워드 ID를 사용합니다.
        String invalidRewardId = "invalid-reward-id";

        // When & Then: 존재하지 않는 리워드를 조회하면 예외가 발생해야 합니다.
        assertThatThrownBy(() -> projectRewardService.getReward(invalidRewardId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("리워드를 찾을 수 없습니다. ID: " + invalidRewardId);
    }

    @Test
    void 프로젝트에_리워드_조회 () {
        // Given: 프로젝트 생성
        ProjectDTO createdProjectDTO = projectService.createProject(); // 프로젝트 생성
        String projectId = createdProjectDTO.getProjectId(); // 생성된 프로젝트의 ID

        // 리워드 추가
        ProjectRewardDTO rewardDTO1 = new ProjectRewardDTO();
        rewardDTO1.setDescription("Reward 1");
        rewardDTO1.setAmount(new BigDecimal("100.00"));
        rewardDTO1.setQuantity(10);
        rewardDTO1.setDeliveryDate(LocalDateTime.now().plusMonths(1));
        projectRewardService.createReward(projectId, rewardDTO1); // 첫 번째 리워드 생성

        ProjectRewardDTO rewardDTO2 = new ProjectRewardDTO();
        rewardDTO2.setDescription("Reward 2");
        rewardDTO2.setAmount(new BigDecimal("200.00"));
        rewardDTO2.setQuantity(5);
        rewardDTO2.setDeliveryDate(LocalDateTime.now().plusMonths(2));
        projectRewardService.createReward(projectId, rewardDTO2); // 두 번째 리워드 생성

        em.flush(); // DB에 반영
        em.clear(); // 영속성 컨텍스트 초기화

        // When: 프로젝트에 속한 리워드 조회
        List<ProjectRewardDTO> rewards = projectRewardService.getRewardsByProject(projectId);

        // Then: 조회된 리워드 개수 검증
        assertThat(rewards).hasSize(2);  // 예상되는 리워드 개수는 2개

        /*
        // 리워드 내용 검증 (첫 번째 리워드)
        assertThat(rewards.get(0).getDescription()).isEqualTo(rewardDTO1.getDescription());
        assertThat(rewards.get(0).getAmount()).isEqualByComparingTo(rewardDTO1.getAmount());
        assertThat(rewards.get(0).getQuantity()).isEqualTo(rewardDTO1.getQuantity());
        assertThat(rewards.get(0).getDeliveryDate()).isEqualTo(rewardDTO1.getDeliveryDate());

        // 리워드 내용 검증 (두 번째 리워드)
        assertThat(rewards.get(1).getDescription()).isEqualTo(rewardDTO2.getDescription());
        assertThat(rewards.get(1).getAmount()).isEqualByComparingTo(rewardDTO2.getAmount());
        assertThat(rewards.get(1).getQuantity()).isEqualTo(rewardDTO2.getQuantity());
        assertThat(rewards.get(1).getDeliveryDate()).isEqualTo(rewardDTO2.getDeliveryDate());
         */
    }

    @Test
    void 리워드_수정_테스트() {
        // Given: 프로젝트를 생성합니다.
        ProjectDTO createdProjectDTO = projectService.createProject(); // 프로젝트 생성
        String projectId = createdProjectDTO.getProjectId(); // 생성된 프로젝트의 ID

        // 프로젝트 엔티티를 조회합니다.
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new AssertionError("프로젝트를 찾을 수 없습니다."));

        // 리워드 DTO 생성
        ProjectRewardDTO rewardDTO = new ProjectRewardDTO();
        rewardDTO.setDescription("Initial Reward Description");
        rewardDTO.setAmount(new BigDecimal("100.00"));
        rewardDTO.setQuantity(10);
        rewardDTO.setDeliveryDate(LocalDateTime.now().plusMonths(1));

        // When: 생성된 프로젝트에 대해 리워드를 생성합니다.
        ProjectRewardDTO createdRewardDTO = projectRewardService.createReward(project.getProjectId(), rewardDTO);

        // 리워드가 DB에 반영되도록 flush 및 clear
        em.flush();
        em.clear();

        // Then: 생성된 리워드를 수정합니다.
        ProjectRewardDTO updatedRewardDTO = new ProjectRewardDTO();
        updatedRewardDTO.setDescription("Updated Reward Description");
        updatedRewardDTO.setAmount(new BigDecimal("150.00"));
        updatedRewardDTO.setQuantity(5);
        updatedRewardDTO.setDeliveryDate(LocalDateTime.now().plusMonths(2));

        // 리워드 수정 서비스 호출
        projectRewardService.updateReward(createdRewardDTO.getRewardId(), updatedRewardDTO);

        // 수정된 리워드가 DB에 반영되도록 flush 및 clear
        em.flush();
        em.clear();

        // 수정된 리워드를 DB에서 조회하여 검증합니다.
        ProjectReward updatedReward = projectRewardRepository.findById(createdRewardDTO.getRewardId())
                .orElseThrow(() -> new AssertionError("리워드를 찾을 수 없습니다."));

        // 필드 검증: 수정된 값이 DB에 반영되었는지 확인
        assertThat(updatedReward.getDescription()).isEqualTo(updatedRewardDTO.getDescription());
        assertThat(updatedReward.getAmount()).isEqualByComparingTo(updatedRewardDTO.getAmount());
        assertThat(updatedReward.getQuantity()).isEqualTo(updatedRewardDTO.getQuantity());
        assertThat(updatedReward.getDeliveryDate()).isEqualTo(updatedRewardDTO.getDeliveryDate());
    }
}