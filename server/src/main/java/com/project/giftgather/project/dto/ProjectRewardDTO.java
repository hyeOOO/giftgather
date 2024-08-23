package com.project.giftgather.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProjectRewardDTO {
    private String rewardId;
    private String description;
    private BigDecimal amount;
    private int quantity;
    private LocalDateTime deliveryDate;
}
