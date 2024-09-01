package com.project.giftgather.project.domain;

import com.project.giftgather.project.dto.ProjectRewardDTO;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "project_rewards")
@Getter
public class ProjectReward {

    @Id
    private String rewardId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String description;

    private BigDecimal amount;

    private int quantity;

    private LocalDateTime deliveryDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //== 생성 메서드 ==//
    public static ProjectReward createReward(Project project, ProjectRewardDTO rewardDTO) {
        ProjectReward reward = new ProjectReward();
        reward.rewardId = UUID.randomUUID().toString();
        reward.project = project;
        reward.description = rewardDTO.getDescription();
        reward.amount = rewardDTO.getAmount();
        reward.quantity = rewardDTO.getQuantity();
        reward.deliveryDate = rewardDTO.getDeliveryDate();
        reward.createdAt = LocalDateTime.now();
        reward.updatedAt = LocalDateTime.now();
        return reward;
    }

    //==비즈니스 메서드==//
    // 리워드 수정 메서드
    public void updateReward(String description, BigDecimal amount, int quantity, LocalDateTime deliveryDate) {
        this.description = description;
        this.amount = amount;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
    }
}