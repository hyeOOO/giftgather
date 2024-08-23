package com.project.giftgather.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "maker_followers")
@Getter
public class MakerFollower {

    @Id
    private String followerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "maker_id")
    private Maker maker;

    private Timestamp createdAt;
}