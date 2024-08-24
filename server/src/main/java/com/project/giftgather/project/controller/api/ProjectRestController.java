package com.project.giftgather.project.controller.api;

import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.dto.ProjectStoryRequest;
import com.project.giftgather.project.dto.ProjectUpdateRequest;
import com.project.giftgather.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProjectDTO> createProject() {
        ProjectDTO project = projectService.createProject();
        return ResponseEntity.ok(project);
    }

    /**
     * 프로젝트 정보 입력 및 수정
     * @param projectId
     * @param updateRequest
     * @return
     */
    @PostMapping("/projectInfo")
    public ResponseEntity<ProjectDTO> updateProjectInfo(@PathVariable String projectId,
                                                        @RequestBody ProjectUpdateRequest updateRequest) {
        ProjectDTO updatedProject = projectService.updateProjectInfo(projectId, updateRequest);
        return ResponseEntity.ok(updatedProject);
    }

    @PostMapping("/projectStory")
    public ResponseEntity<ProjectDTO> updateProjectStory(@PathVariable String projectId,
                                                         @RequestBody ProjectStoryRequest storyRequest) {
        ProjectDTO updatedProject = projectService.updateProjectStory(projectId, storyRequest);
        return ResponseEntity.ok(updatedProject);
    }
}
