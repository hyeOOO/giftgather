package com.project.giftgather.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "user_session")
@Getter
public class UserSession {

    @Id
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String jwtToken;

    private String refreshToken;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
