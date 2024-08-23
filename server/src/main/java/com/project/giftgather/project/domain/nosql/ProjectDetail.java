package com.project.giftgather.project.domain.nosql;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "project_detail")
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
    public static class IntroductionMedia {
        private String type;
        private String url;

        // Getters and Setters
    }

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
    public static ProjectDetail createProjectDetail(String projectId, List<ProjectDetail.Document> documents) {
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.projectId = projectId;
        projectDetail.documents = documents != null ? documents : new ArrayList<>();
        return projectDetail;
    }
}