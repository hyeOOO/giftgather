package com.project.giftgather.user.dto;

import com.project.giftgather.user.domain.Maker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakerDTO {

    private String userId; // User ID, User와의 연관 관계를 나타냅니다.
    private String makerId;
    private String name;   // Maker 이름
    private String bio;    // Maker 소개
    private String profileImage; // Maker 프로필 이미지

    // UsersInfo 관련 필드
    private String email;  // Maker 이메일
    private String phone;  // Maker 전화번호
    private String snsUrl; // Maker SNS URL

    // 엔티티에서 DTO로 변환하는 메서드
    public static MakerDTO fromEntity(Maker maker) {
        MakerDTO dto = new MakerDTO();
        dto.setMakerId(maker.getMakerId());
        dto.setUserId(maker.getUser().getUserId());
        dto.setName(maker.getName());
        dto.setBio(maker.getBio());
        dto.setProfileImage(maker.getProfileImage());
        // email, phone, snsUrl 등 추가 필드 필요 시 설정
        return dto;
    }
}