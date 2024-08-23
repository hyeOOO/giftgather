package com.project.giftgather.project.domain.nosql;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "project_detail")
@Getter
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

    public static class Document {
        private String name;
        private String url;

        // Getters and Setters
    }

    // Getters and Setters for ProjectDetail
}