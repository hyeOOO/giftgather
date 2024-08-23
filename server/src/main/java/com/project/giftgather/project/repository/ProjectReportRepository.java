package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.Category;
import com.project.giftgather.project.domain.ProjectReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectReportRepository extends JpaRepository<ProjectReport, String> {
}
