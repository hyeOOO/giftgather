package com.project.giftgather.order.domain;

import com.project.giftgather.order.domain.status.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
@Getter
public class Payment {

    @Id
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProjectOrder order;

    private BigDecimal amount;

    private String paymentMethod;

    private PaymentStatus paymentStatus;

    private String transactionId;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}