package com.project.giftgather.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SupporterDTO {
    private String supporterId;
    private String userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}
