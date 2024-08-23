package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.Category;
import com.project.giftgather.project.domain.ProjectDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDocumentRepository extends JpaRepository<ProjectDocument, String> {
}
