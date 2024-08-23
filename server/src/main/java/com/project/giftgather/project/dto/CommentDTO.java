package com.project.giftgather.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private String commentId;
    private String userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
