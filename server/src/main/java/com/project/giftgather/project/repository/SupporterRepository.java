package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.Supporter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupporterRepository extends JpaRepository<Supporter, String> {
}
