package com.project.giftgather.project.dto;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.ProjectReward;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProjectRewardDTO {
    private String rewardId;
    private String description;
    private BigDecimal amount; //가격
    private int quantity; //재고
    private LocalDateTime deliveryDate;

    // 엔티티 -> DTO 변환 메서드
    public static ProjectRewardDTO fromEntity(ProjectReward reward) {
        ProjectRewardDTO dto = new ProjectRewardDTO();
        dto.setRewardId(reward.getRewardId());
        dto.setDescription(reward.getDescription());
        dto.setAmount(reward.getAmount());
        dto.setQuantity(reward.getQuantity());
        dto.setDeliveryDate(reward.getDeliveryDate());
        return dto;
    }
}
