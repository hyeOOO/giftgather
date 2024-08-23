package com.project.giftgather.notification.domain;

import com.project.giftgather.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "alarms")
@Getter
public class Alarm {

    @Id
    private String alarmId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String type;

    private String projectId;

    private String message;

    private Timestamp timestamp;

    private boolean read;
}