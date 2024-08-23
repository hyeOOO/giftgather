package com.project.giftgather.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "project_documents")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDocument {

    @Id
    private String documentId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private String documentName;

    private String documentUrl;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}