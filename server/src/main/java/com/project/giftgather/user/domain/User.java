package com.project.giftgather.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.giftgather.project.domain.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    private String userId;

    private String username;

    private String email;

    private String password;

    private String snsProvider;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Maker> makers = new ArrayList<>();

    //== 연관 관계 메서드 ==//
    public void addMaker(Maker maker) {
        makers.add(maker);
        if (maker.getUser() != this) {
            maker.setUser(this); // 양방향 관계 설정
        }
    }
}
