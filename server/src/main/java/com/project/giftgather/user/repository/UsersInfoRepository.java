package com.project.giftgather.user.repository;

import com.project.giftgather.user.domain.nosql.UsersInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersInfoRepository extends MongoRepository<UsersInfo, String> {
    Optional<UsersInfo> findByUserId(String userId);
}
