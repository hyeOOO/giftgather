package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.nosql.ProjectDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailRepository extends MongoRepository<ProjectDetail, String> {
}
