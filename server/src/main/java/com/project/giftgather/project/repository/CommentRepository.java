package com.project.giftgather.project.repository;

import com.project.giftgather.project.domain.Category;
import com.project.giftgather.project.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {
}
