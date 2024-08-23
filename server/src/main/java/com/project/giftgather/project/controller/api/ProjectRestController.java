package com.project.giftgather.project.controller.api;

import com.project.giftgather.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectRestController {

    private final ProjectService projectService;

    /**
     * 프로젝트 생성
     */
    @GetMapping("/create")
    public void createProject() {
        projectService.createProject();
    }
}
