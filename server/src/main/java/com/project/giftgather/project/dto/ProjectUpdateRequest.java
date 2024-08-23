package com.project.giftgather.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProjectUpdateRequest {
    private String title;
    private Long categoryId;
    private BigDecimal goalAmount;
    private List<ProjectDocumentDTO> documents;
}
