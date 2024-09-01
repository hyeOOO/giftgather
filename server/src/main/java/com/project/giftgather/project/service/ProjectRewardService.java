package com.project.giftgather.project.service;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.ProjectReward;
import com.project.giftgather.project.dto.ProjectRewardDTO;
import com.project.giftgather.project.repository.ProjectRepository;
import com.project.giftgather.project.repository.ProjectRewardRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectRewardService {

    private final ProjectRewardRepository projectRewardRepository;
    private final ProjectRepository projectRepository;

    // 리워드 생성
    @Transactional
    public ProjectRewardDTO createReward(String projectId, ProjectRewardDTO rewardDTO) {
        //프로젝트 아이디로 프로젝트를 DB에서 가져옴
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없습니다. ID: " + projectId));

        //리워드 생성 및 프로젝트 설정
        ProjectReward reward = ProjectReward.createReward(project, rewardDTO);

        projectRewardRepository.save(reward);

        return ProjectRewardDTO.fromEntity(reward); // 엔티티 -> DTO 변환
    }

    // 특정 리워드 수정
    @Transactional
    public void updateReward(String rewardId, ProjectRewardDTO updatedRewardDTO) {
        // 1. 리워드를 조회하여 영속성 컨텍스트에 올립니다.
        ProjectReward reward = projectRewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("리워드를 찾을 수 없습니다. ID: " + rewardId));

        // 2. 변경 감지를 위해 엔티티 필드 값을 수정합니다.
        reward.updateReward(
                updatedRewardDTO.getDescription(),
                updatedRewardDTO.getAmount(),
                updatedRewardDTO.getQuantity(),
                updatedRewardDTO.getDeliveryDate()
        );

        // 3. @Transactional에 의해 트랜잭션이 종료될 때 변경 감지가 이루어져 UPDATE 쿼리가 실행됩니다.
    }

    // 특정 리워드 조회
    @Transactional(readOnly = true)
    public ProjectRewardDTO getReward(String rewardId) {
        ProjectReward reward = projectRewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("리워드를 찾을 수 없습니다. ID: " + rewardId));
        return ProjectRewardDTO.fromEntity(reward);
    }

    // 프로젝트에 속한 모든 리워드 조회
    @Transactional(readOnly = true)
    public List<ProjectRewardDTO> getRewardsByProject(String projectId) {
        List<ProjectReward> rewards = projectRewardRepository.findByProjectProjectId(projectId);
        return rewards.stream()
                .map(ProjectRewardDTO::fromEntity)  // 엔티티를 DTO로 변환하는 정적 메서드 호출
                .collect(Collectors.toList());
    }
}
