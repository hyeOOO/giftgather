package com.project.giftgather.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDocumentDTO {
    private String documentId;
    private String documentName;
    private String documentUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
