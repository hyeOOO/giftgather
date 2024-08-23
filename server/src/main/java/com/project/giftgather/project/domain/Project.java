package com.project.giftgather.project.domain;

import com.project.giftgather.project.domain.status.ProjectStatus;
import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.user.domain.Maker;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    private String projectId;

    @Column(unique = true)
    private int projectNumber;

    private String title;

    private String description;

    private BigDecimal goalAmount;

    private BigDecimal currentAmount;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private ProjectStatus projectStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연 로딩 설정
    @JoinColumn(name = "maker_id")
    private Maker maker;

    private String representativeImage;

    private boolean reviewApproval;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryMapping> categoryMappings = new ArrayList<>();

    //==생성 메서드==//
    public static Project createProject(Maker maker) {
        Project project = new Project();
        // UUID 생성 및 출력
        project.projectId = UUID.randomUUID().toString();

        if (maker != null) {
            project.setMaker(maker);  // Maker가 있을 때만 설정
        }
        project.setProjectStatus(ProjectStatus.CREATED);
        project.setCreatedAt(LocalDateTime.now());
        return project;
    }

    //==연관 관계 메서드==//
    public void setMaker(Maker maker) {
        this.maker = maker;
        if (maker != null) {
            maker.getProjects().add(this);
        }
    }

    public void addCategory(Category category) {
        CategoryMapping categoryMapping = new CategoryMapping();
        categoryMapping.setCategory(category);
        categoryMapping.setProject(this);  // 양방향 연관 관계 설정
        this.categoryMappings.add(categoryMapping);
    }
}