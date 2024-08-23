package com.project.giftgather.order.domain;

import com.project.giftgather.order.domain.status.DeliveryStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "deliveries")
public class Delivery {

    @Id
    private String deliveryId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProjectOrder order;

    private DeliveryStatus deliveryStatus;

    private String trackingNumber;

    private String carrier;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}