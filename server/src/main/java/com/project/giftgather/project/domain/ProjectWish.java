package com.project.giftgather.project.domain;

import com.project.giftgather.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "project_wishes")
@Getter
public class ProjectWish {

    @Id
    private String wishId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private Timestamp createdAt;
}