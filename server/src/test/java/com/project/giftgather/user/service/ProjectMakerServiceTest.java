package com.project.giftgather.user.service;

import com.project.giftgather.user.domain.Maker;
import com.project.giftgather.user.domain.nosql.UsersInfo;
import com.project.giftgather.user.dto.MakerDTO;
import com.project.giftgather.user.repository.MakerRepository;
import com.project.giftgather.user.repository.UsersInfoRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ProjectMakerServiceTest {

    @Autowired
    private ProjectMakerService projectMakerService;

    @Autowired
    private MakerRepository makerRepository;

    @Autowired
    private UsersInfoRepository usersInfoRepository;

    @Autowired
    private EntityManager em;

    @Test
    void 메이커_생성_테스트() {
        // given
        MakerDTO makerDTO = new MakerDTO();
        makerDTO.setUserId("user1");
        makerDTO.setName("Test Maker");
        makerDTO.setBio("This is a test bio");
        makerDTO.setProfileImage("https://example.com/profile.jpg");
        makerDTO.setEmail("maker@example.com");
        makerDTO.setPhone("123-456-7890");
        makerDTO.setSnsUrl("https://twitter.com/maker");

        // when
        MakerDTO createdMaker = projectMakerService.createMaker(makerDTO);
        em.flush(); // 영속성 컨텍스트를 DB에 반영
        em.clear(); // 영속성 컨텍스트 초기화

        // then
        Maker makerById = makerRepository.findById(createdMaker.getMakerId())
                .orElseThrow(() -> new AssertionError("Maker not found"));

        assertThat(makerById.getName()).isEqualTo(makerDTO.getName());
        assertThat(makerById.getBio()).isEqualTo(makerDTO.getBio());
        assertThat(makerById.getProfileImage()).isEqualTo(makerDTO.getProfileImage());

        // Verify UsersInfo is updated/created correctly in NoSQL
        var usersInfo = usersInfoRepository.findByUserId(makerDTO.getUserId());
        assertThat(usersInfo).isPresent();
        assertThat(usersInfo.get().getMakerEmail()).isEqualTo(makerDTO.getEmail());
        assertThat(usersInfo.get().getMakerPhone()).isEqualTo(makerDTO.getPhone());
        assertThat(usersInfo.get().getMakerSnsUrl()).isEqualTo(makerDTO.getSnsUrl());
    }

    @Test
    void upsertUsersInfo_기존_UsersInfo_수정_테스트() {
        // given
        MakerDTO makerDTO = new MakerDTO();
        makerDTO.setUserId("user1"); // 기존에 DB에 있는 userId
        makerDTO.setName("Updated Maker");
        makerDTO.setBio("Updated bio");
        makerDTO.setProfileImage("https://example.com/new_profile.jpg");
        makerDTO.setEmail("updated@example.com");
        makerDTO.setPhone("987-654-3210");
        makerDTO.setSnsUrl("https://twitter.com/updated_maker");

        // when
        projectMakerService.upsertUsersInfo(makerDTO);
        em.flush(); // 변경사항을 DB에 반영
        em.clear(); // 영속성 컨텍스트 초기화

        // then
        Optional<UsersInfo> updatedUsersInfo = usersInfoRepository.findByUserId(makerDTO.getUserId());
        assertThat(updatedUsersInfo).isPresent();
        assertThat(updatedUsersInfo.get().getMakerEmail()).isEqualTo(makerDTO.getEmail());
        assertThat(updatedUsersInfo.get().getMakerPhone()).isEqualTo(makerDTO.getPhone());
        assertThat(updatedUsersInfo.get().getMakerSnsUrl()).isEqualTo(makerDTO.getSnsUrl());
    }
}