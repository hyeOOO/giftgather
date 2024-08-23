package com.project.giftgather.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectWishDTO {
    private String wishId;
    private String userId;
    private LocalDateTime createdAt;
}
