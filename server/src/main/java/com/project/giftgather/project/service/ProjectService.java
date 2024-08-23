package com.project.giftgather.project.service;


import com.project.giftgather.project.domain.Project;
import com.project.giftgather.project.dto.ProjectDTO;
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

import java.util.Optional;
import java.util.UUID;

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

        //Project 저장
        Project savedProject = projectRepository.save(project);
        log.info("savedProject={}", savedProject.toString());

        return ProjectDTO.fromEntity(project);
    }

    //프로젝트 각 페이지 마다의 업데이트

    //리워드

    //제출 문서 저장
}
