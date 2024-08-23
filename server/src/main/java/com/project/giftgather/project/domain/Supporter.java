package com.project.giftgather.project.domain;

import com.project.giftgather.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "supporters")
@Getter
public class Supporter {

    @Id
    private String supporterId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal amount;

    private Timestamp createdAt;
}