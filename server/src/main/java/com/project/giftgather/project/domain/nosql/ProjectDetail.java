package com.project.giftgather.project.domain.nosql;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "project_details")
@Getter @Setter
public class ProjectDetail {

    @Id
    private String id;
    private String projectId;
    private String story;
    private IntroductionMedia introductionMedia;
    private List<Update> updates;
    private int wishes;
    private int backers;
    private List<Document> documents;

    // Inner classes for embedded documents
    @Getter @Setter
    public static class IntroductionMedia {
        private String type;
        private String url;

        // Getters and Setters
    }

    @Getter @Setter
    public static class Update {
        private String title;
        private String content;
        private List<String> images;
        private Date createdAt;
        private Date updatedAt;

        // Getters and Setters
    }

    @Data
    public static class Document {
        private String name;
        private String url;
    }

    //==생성 메서드==//
    public static ProjectDetail createProjectDetail(String projectId) {
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setProjectId(projectId);
        // 필요시 다른 필드 초기화
        return projectDetail;
    }
}