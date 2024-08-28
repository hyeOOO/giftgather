package com.project.giftgather.project.controller.api;

import com.project.giftgather.project.dto.ProjectDTO;
import com.project.giftgather.project.dto.ProjectRewardDTO;
import com.project.giftgather.project.dto.ProjectStoryRequest;
import com.project.giftgather.project.dto.ProjectUpdateRequest;
import com.project.giftgather.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
@Tag(name = "ProjectCreate", description = "프로젝트 생성 관련 API")
public class ProjectRestController {

    private final ProjectService projectService;

    /**
     * 프로젝트 생성
     */
    @GetMapping("/create")
    @Operation(summary = "Create Project, ProjectDetail", description = "프로젝트 생성 및 프로젝트 디테일 생성")
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
    @PostMapping("/{projectId}/projectInfo")
    @Operation(summary = "update preojectInfo", description = "프로젝트 정보 입력 및 수정")
    public ResponseEntity<ProjectDTO> updateProjectInfo(@PathVariable String projectId,
                                                        @RequestBody ProjectUpdateRequest updateRequest) {
        ProjectDTO updatedProject = projectService.updateProjectInfo(projectId, updateRequest);
        return ResponseEntity.ok(updatedProject);
    }

    /**
     * 프로젝트 스토리 작성
     * @param projectId
     * @param storyRequest
     * @return
     */
    @PostMapping("/{projectId}/projectStory")
    @Operation(summary = "update projectStory", description = "프로젝트 스토리 입력 및 수정")
    public ResponseEntity<ProjectDTO> updateProjectStory(@PathVariable String projectId,
                                                         @RequestBody ProjectStoryRequest storyRequest) {
        ProjectDTO updatedProject = projectService.updateProjectStory(projectId, storyRequest);
        return ResponseEntity.ok(updatedProject);
    }
}
