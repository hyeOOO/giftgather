package com.project.giftgather.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDocumentDTO {
    private String documentId;
    private String documentName;
    private String documentUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
