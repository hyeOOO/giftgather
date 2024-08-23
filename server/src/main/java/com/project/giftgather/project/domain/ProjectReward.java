package com.project.giftgather.project.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
}