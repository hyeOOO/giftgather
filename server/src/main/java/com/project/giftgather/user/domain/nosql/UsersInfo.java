package com.project.giftgather.user.domain.nosql;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "users_info")
@Getter
public class UsersInfo {

    @Id
    private String id;

    @Field("user_id")
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

    // 정보 업데이트 메서드
    public void updateMakerInfo(String name, String bio, String profileImage, String email, String phone, String snsUrl) {
        this.makerName = name;
        this.makerBio = bio;
        this.makerProfileImg = profileImage;
        this.makerEmail = email;
        this.makerPhone = phone;
        this.makerSnsUrl = snsUrl;
        this.updatedAt = new Date();  // 수정된 시간 갱신
    }

    // Getters and Setters
}