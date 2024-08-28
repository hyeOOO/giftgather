package com.project.giftgather.project.controller.api;

import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.dto.ProjectRewardDTO;
import com.project.giftgather.project.service.ProjectRewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/rewards")
@RequiredArgsConstructor
@Tag(name = "ProjectReward", description = "프로젝트 리워드 관련 API")
public class ProjectRewardRestController {

    private final ProjectRewardService projectRewardService;

    // 리워드 생성
    @PostMapping
    @Operation(summary = "Create Project Reward", description = "프로젝트 리워드 생성")
    public ResponseEntity<ProjectRewardDTO> createReward(@PathVariable String projectId, @RequestBody ProjectRewardDTO rewardDTO) {
        ProjectRewardDTO createdReward = projectRewardService.createReward(projectId, rewardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReward);
    }

    /*
    // 특정 리워드 조회
    @GetMapping("/{rewardId}")
    public ResponseEntity<ProjectRewardDTO> getReward(@PathVariable String rewardId) {
        ProjectRewardDTO rewardDTO = projectRewardService.getReward(rewardId);
        return ResponseEntity.ok(rewardDTO);
    }

    // 프로젝트에 속한 모든 리워드 조회
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ProjectRewardDTO>> getRewardsByProject(@PathVariable String projectId) {
        List<ProjectRewardDTO> rewardDTOs = projectRewardService.getRewardsByProject(projectId);
        return ResponseEntity.ok(rewardDTOs);
    }
     */
}
