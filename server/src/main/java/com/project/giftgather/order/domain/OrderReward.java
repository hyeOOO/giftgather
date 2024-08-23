package com.project.giftgather.order.domain;

import com.project.giftgather.project.domain.ProjectReward;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "order_rewards")
@Getter
public class OrderReward {

    @Id
    private String orderRewardId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProjectOrder order;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private ProjectReward reward;

    private int quantity;

    private BigDecimal amount;

    private Timestamp createdAt;
}