package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.ProjectReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRewardRepository extends JpaRepository<ProjectReward, String> {
    List<ProjectReward> findByProjectProjectId(String projectId);
}
