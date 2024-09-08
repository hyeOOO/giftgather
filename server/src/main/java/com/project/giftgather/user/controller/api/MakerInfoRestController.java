package com.project.giftgather.user.controller.api;

import com.project.giftgather.user.dto.MakerDTO;
import com.project.giftgather.user.service.ProjectMakerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/project/maker")
@RestController
@RequiredArgsConstructor
@Tag(name = "ProjectMakerInfo", description = "프로젝트 메이커 정보 관련 API")
public class MakerInfoRestController {

    private final ProjectMakerService projectMakerService;

    //메이커 생성 요청
    @PostMapping("/create")
    public ResponseEntity<MakerDTO> createMaker(@RequestBody MakerDTO makerDTO) {
        MakerDTO createMaker = projectMakerService.createMaker(makerDTO);
        return new ResponseEntity<>(createMaker, HttpStatus.CREATED);
    }
}
