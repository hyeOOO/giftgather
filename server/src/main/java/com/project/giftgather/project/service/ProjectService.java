package com.project.giftgather.project.service;


import com.project.giftgather.project.domain.Category;
import com.project.giftgather.project.domain.CategoryMapping;
import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.domain.nosql.ProjectDetail;
import com.project.giftgather.project.dto.*;
import com.project.giftgather.project.repository.CategoryRepository;
import com.project.giftgather.project.repository.ProjectDetailRepository;
import com.project.giftgather.project.repository.ProjectRepository;
import com.project.giftgather.project.repository.ProjectRepositoryCustom;
import com.project.giftgather.user.domain.Maker;
import com.project.giftgather.user.domain.User;
import com.project.giftgather.user.repository.MakerRepository;
import com.project.giftgather.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectRepositoryCustom projectRepositoryCustom;
    private final ProjectDetailRepository projectDetailRepository;
    private final MakerRepository makerRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 프로젝트 생성
     * @return
     */
    @Transactional
    public ProjectDTO createProject() {
        //사용자 정보 추출 필요없는 거 같음
        //토큰으로 가져올 예정
        //String userId = getCurrentUserId();
        /*String userId = "user1";
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        Maker maker = Maker.createMaker(user);*/

        //DB에서 가장 큰 프로젝트 넘버 조회 + 1
        int newProjectNumber = projectRepositoryCustom.findMaxProjectNumber() + 1;

        //Project 생성
        Project project = Project.createProject(null);
        project.setProjectNumber(newProjectNumber);
        log.info("createdProject={}", project.toString());

        // ProjectDetail 간단히 생성 (프로젝트 ID만 설정)
        ProjectDetail projectDetail = ProjectDetail.createProjectDetail(project.getProjectId());
        projectDetailRepository.save(projectDetail);

        //Project 저장
        Project savedProject = projectRepository.save(project);
        log.info("savedProject={}", savedProject.toString());
        log.info("savecProjectDetatil={}", projectDetail.toString());

        return ProjectDTO.fromEntity(project, ProjectDetailDTO.convertToProjectDetailDTO(projectDetail));
    }

    //프로젝트 각 페이지 마다의 업데이트
    //프로젝트 정보 페이지 저장
    @Transactional
    public ProjectDTO updateProjectInfo(String projectId, ProjectUpdateRequest updateRequest) {
        //1. RDBMS에서 프로젝트를 찾아 수정
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("프로젝트 아이디로 프로젝트를 찾을 수 없습니다. " + projectId));
        project.setGoalAmount(updateRequest.getGoalAmount());
        project.setUpdatedAt(LocalDateTime.now());

        //2. 카테고리 설정
        Category category = categoryRepository.findById(updateRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 아이디로 카테고리를 찾을 수 없습니다. " + updateRequest.getCategoryId()));

        //프로젝트의 카테고리 저장
        project.addCategory(category);

        //프로젝트 저장
        projectRepository.save(project);

        //3. MongoDB에서 프로젝트 상세 정보를 수정
        ProjectDetail projectDetail = projectDetailRepository.findByProjectId(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 상세 정보를 찾을 수 없습니다. " + projectId));

        //파일처리가 필요한가?
        // 여기서 필요한 필드만 업데이트
        List<ProjectDetail.Document> updatedDocuments = updateRequest.getDocuments().stream()
                .map(doc -> {
                    ProjectDetail.Document document = new ProjectDetail.Document();
                    document.setName(doc.getDocumentName());  // ProjectDocumentDTO의 필드 이름과 일치하도록 수정
                    document.setUrl(doc.getDocumentUrl());
                    return document;
                }).collect(Collectors.toList());

        projectDetail.setDocuments(updatedDocuments);
        //추가적으로 필요하면 여기 추가

        projectDetailRepository.save(projectDetail);

        // 4. 변경된 내용을 DTO로 반환
        return ProjectDTO.fromEntity(project, ProjectDetailDTO.convertToProjectDetailDTO(projectDetail));
    }

    //프로젝트 스토리 작성
    public ProjectDTO updateProjectStory(String projectId, ProjectStoryRequest storyRequest) {
        //1. RDBMS에서 프로젝트를 찾아 수정
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("프로젝트 아이디로 프로젝트를 찾을 수 없습니다. " + projectId));
        project.setTitle(storyRequest.getTitle());
        project.setDescription(storyRequest.getDescription());
        project.setUpdatedAt(LocalDateTime.now());
        project.setRepresentativeImage(storyRequest.getRepresentativeImage());
        project.setReviewApproval(storyRequest.isReviewApproval());

        //프로젝트 저장
        projectRepository.save(project);

        // 2. MongoDB에서 프로젝트 상세 정보를 수정
        ProjectDetail projectDetail = projectDetailRepository.findByProjectId(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 상세 정보를 찾을 수 없습니다. " + projectId));

        // 필요시 projectDetail 업데이트
        projectDetail.setStory(storyRequest.getStory());

        // 3. IntroductionMedia 수정 또는 생성
        ProjectDetail.IntroductionMedia introductionMedia = projectDetail.getIntroductionMedia();
        if (introductionMedia == null) {
            introductionMedia = new ProjectDetail.IntroductionMedia();
            projectDetail.setIntroductionMedia(introductionMedia);
        }

        introductionMedia.setType(storyRequest.getType()); // 요청으로부터 타입 가져오기
        introductionMedia.setUrl(storyRequest.getFile());   // 요청으로부터 URL 가져오기

        // 4. ProjectDetail 저장
        projectDetailRepository.save(projectDetail);

        // 5. 변경된 내용을 DTO로 반환
        return ProjectDTO.fromEntity(project, ProjectDetailDTO.convertToProjectDetailDTO(projectDetail));
    }

    //리워드

    //제출 문서 저장
}
