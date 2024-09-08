package com.project.giftgather.user.service;

import com.project.giftgather.user.domain.Maker;
import com.project.giftgather.user.domain.User;
import com.project.giftgather.user.domain.nosql.UsersInfo;
import com.project.giftgather.user.dto.MakerDTO;
import com.project.giftgather.user.repository.MakerRepository;
import com.project.giftgather.user.repository.UsersInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectMakerService {

    private final MakerRepository makerRepository;
    private final UsersInfoRepository usersInfoRepository;

    //Maker 생성
    @Transactional
    public MakerDTO createMaker(MakerDTO makerDTO) {
        //User는 더미 사용 이후 토큰으로 가져올 예정
        User user = new User();
        user.setUserId("user1");

        //Maker 생성
        Maker maker = Maker.createMaker(user, makerDTO.getName(), makerDTO.getBio(), makerDTO.getProfileImage());
        makerRepository.save(maker);

        // UsersInfo 생성/수정
        upsertUsersInfo(makerDTO);

        // UsersInfo를 조회하여 MakerDTO에 추가
        Optional<UsersInfo> usersInfoOpt = usersInfoRepository.findByUserId(makerDTO.getUserId());

        // UsersInfo가 존재하면 필요한 정보를 추가하여 MakerDTO 반환
        MakerDTO resultDTO = MakerDTO.fromEntity(maker);
        usersInfoOpt.ifPresent(usersInfo -> {
            resultDTO.setEmail(usersInfo.getMakerEmail());
            resultDTO.setPhone(usersInfo.getMakerPhone());
            resultDTO.setSnsUrl(usersInfo.getMakerSnsUrl());
        });

        // 생성된 Maker 엔티티를 DTO로 변환하여 반환
        return resultDTO;
    }

    // Maker 수정 메서드
    /*@Transactional
    public void updateMaker(String makerId, MakerDTO updatedMakerDTO) {
        // Maker 조회 및 수정
        Maker maker = makerRepository.findById(makerId)
                .orElseThrow(() -> new IllegalArgumentException("Maker를 찾을 수 없습니다. ID: " + makerId));
        maker.updateMaker(updatedMakerDTO.getName(), updatedMakerDTO.getBio(), updatedMakerDTO.getProfileImage());

        // UsersInfo 수정
        upsertUsersInfo(updatedMakerDTO);
    }*/

    // UsersInfo 저장/수정 메서드
    public void upsertUsersInfo(MakerDTO makerDTO) {
        // NoSQL에서 UserInfo 찾기
        Optional<UsersInfo> optionalUsersInfo = usersInfoRepository.findByUserId(makerDTO.getUserId());

        // 존재 여부에 따라 생성 또는 수정
        UsersInfo usersInfo = optionalUsersInfo.orElse(new UsersInfo());
        usersInfo.updateMakerInfo(
                makerDTO.getName(),
                makerDTO.getBio(),
                makerDTO.getProfileImage(),
                makerDTO.getEmail(),
                makerDTO.getPhone(),
                makerDTO.getSnsUrl()
        );

        // NoSQL 저장
        usersInfoRepository.save(usersInfo);
    }
}
