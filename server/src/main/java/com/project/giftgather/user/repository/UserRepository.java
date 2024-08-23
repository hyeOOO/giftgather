package com.project.giftgather.user.repository;

import com.project.giftgather.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
