package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.Category;
import com.project.giftgather.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
