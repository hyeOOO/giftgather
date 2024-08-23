package com.project.giftgather.order.domain;

import com.project.giftgather.order.domain.status.OrderStatus;
import com.project.giftgather.project.domain.Project;
import com.project.giftgather.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "project_orders")
@Getter
public class ProjectOrder {

    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal totalAmount;

    private OrderStatus orderStatus;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}