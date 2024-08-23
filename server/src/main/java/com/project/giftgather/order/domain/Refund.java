package com.project.giftgather.order.domain;

import com.project.giftgather.order.domain.status.RefundStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "refunds")
@Getter
public class Refund {

    @Id
    private String refundId;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private BigDecimal amount;

    private String refundReason;

    private RefundStatus refundStatus;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}