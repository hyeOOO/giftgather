package com.project.giftgather.user.domain.nosql;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users_info")
public class UsersInfo {

    @Id
    private String id;

    private String userId;  // 외래 키처럼 참조 ID로 사용

    private String supporterName;
    private String supporterBio;
    private String supporterProfileImg;

    private String makerName;
    private String makerBio;
    private String makerProfileImg;
    private String makerEmail;
    private String makerPhone;
    private String makerSnsUrl;

    private Date createdAt;
    private Date updatedAt;

    // Getters and Setters
}