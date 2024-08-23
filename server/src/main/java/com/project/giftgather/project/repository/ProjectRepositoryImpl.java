package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.QProject;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom{

    private final EntityManager em;

    @Override
    public int findMaxProjectNumber() {
        QProject project = QProject.project;
        JPAQueryFactory query = new JPAQueryFactory(em);

        Integer maxProjectNumber = query
                .select(project.projectNumber.max())
                .from(project)
                .fetchOne();

        return maxProjectNumber != null ? maxProjectNumber : 0;
    }
}
