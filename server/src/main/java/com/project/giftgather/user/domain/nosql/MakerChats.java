package com.project.giftgather.user.domain.nosql;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "maker_chats")
@Getter
public class MakerChats {

    @Id
    private String id;

    private String makerId;  // 외래 키처럼 참조 ID로 사용
    private String userId;   // 외래 키처럼 참조 ID로 사용

    private List<Message> messages;

    public static class Message {
        private String senderId;  // 외래 키처럼 참조 ID로 사용
        private String message;
        private Date timestamp;

        // Getters and Setters
    }

    // Getters and Setters
}