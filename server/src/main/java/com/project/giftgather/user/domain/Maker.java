package com.project.giftgather.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.giftgather.project.domain.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "makers")
@Getter @Setter
public class Maker {

    @Id
    private String makerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String name;

    private String bio;

    private String profileImage;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "maker", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    //== 생성 메서드 ==//
    public static Maker createMaker(User user, String name, String bio, String profileImage) {
        Maker maker = new Maker();
        maker.makerId = UUID.randomUUID().toString();
        maker.user = user;
        maker.name = name;
        maker.bio = bio;
        maker.profileImage = profileImage;
        maker.createdAt = LocalDateTime.now();
        maker.updatedAt = LocalDateTime.now();
        return maker;
    }

    //==비지니스 메서드==//
    //수정 메소드
    public void updateMaker(String name, String bio, String profileImage) {
        this.name = name;
        this.bio = bio;
        this.profileImage = profileImage;
        this.updatedAt = LocalDateTime.now();
    }

    //== 연관 관계 메서드 ==//
    public void addProject(Project project) {
        projects.add(project);
        project.setMaker(this); // 양방향 연관 관계 설정
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null && !user.getMakers().contains(this)) {
            user.addMaker(this); // 양방향 관계 설정
        }
    }
}