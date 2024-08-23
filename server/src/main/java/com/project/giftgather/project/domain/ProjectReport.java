package com.project.giftgather.project.domain;

import com.project.giftgather.project.domain.status.ProjectReportStatus;
import com.project.giftgather.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

//프로젝트 신고
@Entity
@Table(name = "project_reports")
@Getter
public class ProjectReport {

    @Id
    private String reportId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String reportReason;

    private ProjectReportStatus projectReportStatus;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}