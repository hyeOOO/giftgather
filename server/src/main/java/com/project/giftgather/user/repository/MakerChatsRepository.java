package com.project.giftgather.user.repository;

import com.project.giftgather.user.domain.nosql.MakerChats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakerChatsRepository extends MongoRepository<MakerChats, String> {
}
