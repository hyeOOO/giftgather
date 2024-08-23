package com.project.giftgather.project.domain;

import com.project.giftgather.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@Getter
public class Comment {

    @Id
    private String commentId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}