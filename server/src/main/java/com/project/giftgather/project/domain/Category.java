package com.project.giftgather.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //==생성 메서드==//
    public static Category createCategory(String categoryName) {
        Category category = new Category();
        category.categoryName = categoryName;
        category.createdAt = LocalDateTime.now();
        category.updatedAt = LocalDateTime.now();
        return category;
    }

    //== 변경 메서드 ==//
    public void updateCategoryName(String categoryName) {
        this.categoryName = categoryName;
        this.updatedAt = LocalDateTime.now();
    }
}