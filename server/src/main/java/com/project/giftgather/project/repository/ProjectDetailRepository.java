package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.nosql.ProjectDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ProjectDetailRepository extends MongoRepository<ProjectDetail, String> {
    Optional<ProjectDetail> findByProjectId(String projectId);
}
