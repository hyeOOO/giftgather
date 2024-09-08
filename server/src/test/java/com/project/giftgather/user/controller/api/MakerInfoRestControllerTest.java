package com.project.giftgather.user.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.giftgather.user.dto.MakerDTO;
import com.project.giftgather.user.service.ProjectMakerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MakerInfoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectMakerService projectMakerService;

    @Test
    void 메이커_생성_테스트() throws Exception {
        // Given: 테스트용 MakerDTO 생성
        MakerDTO makerDTO = new MakerDTO();
        makerDTO.setUserId("user1");
        makerDTO.setName("Test Maker");
        makerDTO.setBio("This is a test bio");
        makerDTO.setProfileImage("https://example.com/profile.jpg");
        makerDTO.setEmail("maker@example.com");
        makerDTO.setPhone("123-456-7890");
        makerDTO.setSnsUrl("https://twitter.com/maker");

        // When: 메이커 생성 API 호출
        mockMvc.perform(MockMvcRequestBuilders.post("/api/project/maker/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(makerDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Maker"))
                .andExpect(jsonPath("$.bio").value("This is a test bio"))
                .andExpect(jsonPath("$.email").value("maker@example.com"))
                .andExpect(jsonPath("$.phone").value("123-456-7890"))
                .andExpect(jsonPath("$.snsUrl").value("https://twitter.com/maker"));
    }
}